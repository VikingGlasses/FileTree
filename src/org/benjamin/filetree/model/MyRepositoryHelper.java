package org.benjamin.filetree.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import org.benjamin.filetree.model.entity.Branch;
import org.benjamin.filetree.model.repository.BranchRepositoryI;
import org.benjamin.filetree.model.repository.BranchRepositoryImpl;
import org.benjamin.filetree.model.repository.JpaExecutionContainer;
import org.benjamin.filetree.model.repository.JpaExecutionContainerImpl;
import org.benjamin.filetree.model.repository.LeafRepositoryI;
import org.benjamin.filetree.model.repository.LeafRepositoryImpl;

/**
 * Quick and dirty implementation of RepositoryHelper.
 * Warning inefficient search algorithm.
 * Depends on BranchRepositoryI and LeafRepositoryI
 * 
 * @see BranchRepositoryI
 * @see LeafRepositoryI
 * @author benjamin
 *
 */
public class MyRepositoryHelper implements RepositoryHelper {
  
  private BranchRepositoryI branchRepo;
  private LeafRepositoryI leafRepo;
  
  /**
   * Creates and uses a implementation of BranchRepositoryI and LeafRepositoryI.
   */
  public MyRepositoryHelper() {
    JpaExecutionContainer container = new JpaExecutionContainerImpl();
    branchRepo = new BranchRepositoryImpl(container);
    leafRepo = new LeafRepositoryImpl(container);
  }

  @Override
  public TreeComponent createNewBranch(int parentId) {
    return branchRepo.add("New Branch", parentId);
  }

  @Override
  public TreeComponent createNewLeaf(int parentId) {
    return leafRepo.add("New Leaf", parentId);
  }

  @Override
  public boolean removeLeaf(int id) {
    return leafRepo.delete(id);
  }

  @Override
  public boolean removeBranch(int id) {
    return branchRepo.delete(id);
  }

  @Override
  public boolean renameBranch(int id, String newName) {
    return branchRepo.changeName(id, newName);
  }

  @Override
  public boolean renameLeaf(int id, String newName) {
    return leafRepo.changeName(id, newName);
  }

  /**
   * Warning bad implementation: Performs 3*n number of queries. Where n is the number of sub-branches.  
   */
  @Override
  public Set<TreeComponent> search(int rootId, String text) {
    // 
    Set<TreeComponent> result = new TreeSet<>(new TreeComponentComperator());
    Queue<Branch> queue = new LinkedList<>();
    queue.offer(branchRepo.get(rootId));
    while (!queue.isEmpty()) {
      Branch branch = queue.poll();
      queue.addAll(branchRepo.getSubBranchesFrom(branch.getId()));
      result.addAll(branchRepo.search(branch.getId(), text));
      result.addAll(leafRepo.search(branch.getId(), text));
    }
    return result;
  }

  @Override
  public Set<TreeComponent> getChildrenFrom(int id) {
    Set<TreeComponent> result = new TreeSet<>(new TreeComponentComperator());
    result.addAll(branchRepo.getSubBranchesFrom(id));
    result.addAll(branchRepo.getLeafsFrom(id));
    return result;
  }

}

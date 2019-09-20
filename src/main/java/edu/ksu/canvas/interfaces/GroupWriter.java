package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Group;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by dvasjuta on AUG/15/2019.
 */
public interface GroupWriter extends CanvasWriter<Group, GroupWriter> {

    /**
     * Create a new Group Category in Canvas.
     *
     * @param courseId identifies the parent course we are adding this group to
     * @param group a Group object containing the information needed to create
     *         the Group in Canvas
     * @return the newly created Group
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Group> createGroup(String courseId, Group group) throws IOException;

	/**
     * Create a new Group Category in Canvas.
     *
     * @param courseId identifies the parent course we are adding this group to
     * @param groupCategoryId identifies the parent group category id we are adding this group to
     * @param group a Group object containing the information needed to create
     *         the Group in Canvas
     * @return the newly created Group
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Group> createGroup(String courseId, String groupCategoryId, Group group) throws IOException;

    /**
     * Update a group in Canvas.
     *
     * @param group group object with changes made that will be persisted
     * @return the updated group
     * @throws IOException when there is an error communicating with Canvas
     */
    Optional<Group> updateGroup(Group group) throws IOException;

    /**
     * Delete an existing group in Canvas.
     *
     * @param groupId identifies the group to delete
     * @return the Group object that was deleted
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Group> deleteGroup(String groupId) throws IOException;
}
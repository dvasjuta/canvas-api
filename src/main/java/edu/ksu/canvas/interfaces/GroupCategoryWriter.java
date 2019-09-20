package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.GroupCategory;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by dvasjuta on AUG/15/2019.
 */
public interface GroupCategoryWriter extends CanvasWriter<GroupCategory, GroupCategoryWriter> {

    /**
     * Create a new Group Category in Canvas.
     *
     * @param courseId identifies the parent course we are adding this group category to
     * @param groupCategory a Group Category object containing the information needed to create
     *         the Group Category in Canvas
     * @return the newly created Group Category
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<GroupCategory> createGroupCategory(String courseId, GroupCategory groupCategory) throws IOException;

	/**
     * Update a group category in Canvas.
     *
     * @param groupCategory group category object with changes made that will be persisted
     * @return the updated group category
     * @throws IOException when there is an error communicating with Canvas
     */
    Optional<GroupCategory> updateGroupCategory(GroupCategory groupCategory) throws IOException;

    /**
     * Delete an existing group category in Canvas.
     *
     * @param groupCategoryId identifies the group category to delete
     * @return the Group Category object that was deleted
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<GroupCategory> deleteGroupCategory(String groupCategoryId) throws IOException;
}
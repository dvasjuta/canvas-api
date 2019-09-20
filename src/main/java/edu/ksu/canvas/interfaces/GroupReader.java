package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Group;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by dvasjuta on AUG/15/2019.
 */
public interface GroupReader extends CanvasReader<Group, GroupReader> {

	/**
	 * Get a list of groups in a course.
	 *
	 * @param courseId Canvas ID of the course
	 * @return List of groups objects (without a body)
	 * @throws IOException When there is an error communicating with Canvas
	 */
	List<Group> listGroups(String courseId) throws IOException;

	/**
	 * Get a list of groups in a group category.
	 *
	 * @param groupCategoryId Canvas ID of the course
	 * @return List of groups objects (without a body)
	 * @throws IOException When there is an error communicating with Canvas
	 */
	List<Group> listGroupsInGroupCategory(Integer groupCategoryId) throws IOException;

	/**
	 * Retrieve a specific group from Canvas.
	 *
	 * @param id Canvas ID of the group object
	 * @return The requested group object
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<Group> getGroup(Integer id) throws IOException;
}

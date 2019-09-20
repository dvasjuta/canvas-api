package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.GroupCategory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by dvasjuta on AUG/15/2019.
 */
public interface GroupCategoryReader extends CanvasReader<GroupCategory, GroupCategoryReader> {
    /**
     * Get a list of group categories in a course.
     * @param courseId Canvas ID of the course
     * @return List of GroupCategory objects (without a body)
     * @throws IOException When there is an error communicating with Canvas
     */
     List<GroupCategory> listGroupCategories(String courseId) throws IOException;

	/**
	 * Retrieve a specific Group Category from Canvas.
	 *
	 * @param id Canvas ID of the Group Category object
	 * @return The requested Group Category object
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<GroupCategory> getGroupCategory(Integer id) throws IOException;
}

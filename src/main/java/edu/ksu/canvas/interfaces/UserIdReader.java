/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.UserId;
import edu.ksu.canvas.requestOptions.GetUsersInCourseOptions;
import java.io.IOException;
import java.util.List;

/**
 * UserIdReader
 * -- Description --
 *
 * @version $Revision$
 * @author David V (last $Author$)
 * $Id$
 */
public interface UserIdReader extends CanvasReader<UserId, UserIdReader> {
    /**
     * Retrieve a list of users in a course
     * @param options API options for this API call
     * @return List of users in a course
     * @throws IOException When there is an error communicating with Canvas
     */
    List<UserId> getUsersInCourse(GetUsersInCourseOptions options) throws IOException;
}

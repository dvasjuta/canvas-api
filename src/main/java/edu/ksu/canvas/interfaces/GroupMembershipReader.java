package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.GroupMembership;

import java.io.IOException;
import java.util.List;

/**
 * Created by dvasjuta on AUG/15/2019.
 */
public interface GroupMembershipReader extends CanvasReader<GroupMembership, GroupMembershipReader> {
    /**
     * Get a list of group membership in a group.
     * @param groupId Canvas ID of the group
     * @return List of group membership objects (without a body)
     * @throws IOException When there is an error communicating with Canvas
     */
     List<GroupMembership> listGroupMembership(Integer groupId) throws IOException;

    /**
     * Get a list of group membership in a group category.
     * @param groupCategoryId Canvas ID of the group category
     * @return List of groups membership objects (without a body)
     * @throws IOException When there is an error communicating with Canvas
     */
     List<GroupMembership> listGroupMemebershipInGroupCategory(Integer groupCategoryId) throws IOException;
}

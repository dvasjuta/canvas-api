package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.GroupMembership;
import edu.ksu.canvas.requestOptions.UnEnrollOptions;

import java.io.IOException;
import java.util.Optional;

public interface GroupMembershipWriter extends CanvasWriter<GroupMembership, GroupMembershipWriter> {

	/**
	 * Enrolls a user in a group.
	 *
	 * @param groupMembership partially populated group membership object
	 * @return optional group membership
	 * @throws IllegalArgumentException when the enrollment groupID or userId is
	 * not set
	 * @throws IOException if there is an error communicating with Canvas
	 */
	Optional<GroupMembership> enrollUserInGroup(GroupMembership groupMembership) throws IllegalArgumentException, IOException;

	/**
	 * Enrolls a user in a group category.
	 *
	 * @param groupCategoryId group category ID
	 * @param groupMembership partially populated group membership object
	 * @return optional group membership
	 * @throws IllegalArgumentException when the enrollment groupID or userId is
	 * not set
	 * @throws IOException if there is an error communicating with Canvas
	 */
	Optional<GroupMembership> enrollUserInGroupCategory(Integer groupCategoryId, GroupMembership groupMembership) throws IllegalArgumentException, IOException;

	/**
	 *
	 * Drop a user group membership in a group // using the Unenrollment option
	 * of DELETE.
	 *
	 * @param groupMembershipId id - Canvas group membership identifier
	 * @param groupId - Canvas group identifier
	 * @return Populated membership Dropped when successful
	 * @throws IOException if there is an error communicating with Canvas
	 */
	boolean dropGroupMembership(String groupMembershipId, String groupId) throws IOException;

	/**
	 *
	 * Drop a user group membership in a group // using the Unenrollment option
	 * of DELETE.
	 *
	 * @param groupCategoryId - Canvas group identifier
	 * @param groupMembershipId id - Canvas group membership identifier
	 * @return Populated Membership Dropped when successful
	 * @throws IOException if there is an error communicating with Canvas
	 */
	//Optional<GroupMembership> dropGroupMembershipInCategory(String groupCategoryId, String groupMembershipId) throws IOException;


	/**
	 * Update membership. Useful for changing workflow_state.
	 * @param groupMembership populated group membership object
	 * @return optional group membership
	 * @throws IllegalArgumentException when the enrollment groupID or userId is
	 * not set
	 * @throws IOException if there is an error communicating with Canvas
	 */
	Optional<GroupMembership> updateGroupMembership(GroupMembership groupMembership) throws IllegalArgumentException, IOException;

}

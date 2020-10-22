/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ksu.canvas.tests.group;

import edu.ksu.canvas.CanvasTestBase;
import static edu.ksu.canvas.CanvasTestBase.DEFAULT_PAGINATION_PAGE_SIZE;
import static edu.ksu.canvas.CanvasTestBase.SOME_CONNECT_TIMEOUT;
import static edu.ksu.canvas.CanvasTestBase.SOME_OAUTH_TOKEN;
import static edu.ksu.canvas.CanvasTestBase.SOME_READ_TIMEOUT;
import edu.ksu.canvas.impl.GroupCategoryImpl;
import edu.ksu.canvas.impl.GroupImpl;
import edu.ksu.canvas.impl.GroupMembershipImpl;
import edu.ksu.canvas.interfaces.GroupCategoryReader;
import edu.ksu.canvas.interfaces.GroupCategoryWriter;
import edu.ksu.canvas.interfaces.GroupMembershipReader;
import edu.ksu.canvas.interfaces.GroupMembershipWriter;
import edu.ksu.canvas.interfaces.GroupReader;
import edu.ksu.canvas.interfaces.GroupWriter;
import edu.ksu.canvas.model.Group;
import edu.ksu.canvas.model.GroupCategory;
import edu.ksu.canvas.model.GroupMembership;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GroupUTest extends CanvasTestBase {

	private static final String COURSE_ID = "4";

//	@Autowired
//	private FakeRestClient fakeRestClient;
	private GroupCategoryReader groupCategoryReader;
	private GroupReader groupReader;
	private GroupMembershipReader groupMembershipReader;

	private GroupCategoryWriter groupCategoryWriter;
	private GroupWriter groupWriter;
	private GroupMembershipWriter groupMembershipWriter;

	@Before
	public void setupData() {
		groupCategoryReader = new GroupCategoryImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
		groupReader = new GroupImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
		groupMembershipReader = new GroupMembershipImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);

		groupCategoryWriter = new GroupCategoryImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
		groupWriter = new GroupImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
		groupMembershipWriter = new GroupMembershipImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);

	}

	@Test
	public void testGettingCourseGroups() throws Exception {
		String url = baseUrl + "/api/v1/courses/" + COURSE_ID + "/groups";
		fakeRestClient.addSuccessResponse(url, "SampleJson/group/CourseGroups.json");
		List<Group> courseGroups = groupReader.listGroups("4");
		Assert.assertEquals("Expected that 6 groups are in the course", 6, courseGroups.size());
		Group firstGroup = courseGroups.get(0);
		Assert.assertEquals("Expected id in object to match id in json", new Integer(1111), firstGroup.getId());
		Assert.assertEquals("Expected name in object to match name in json", "TEST-ALPHA", firstGroup.getName());
		Assert.assertEquals("Expected groupCategoryId in object to match in json", new Integer(25), firstGroup.getGroupCategoryId());
	}

	@Test
	public void testGettingCourseGroupCategories() throws Exception {
		String url = baseUrl + "/api/v1/courses/" + COURSE_ID + "/group_categories";
		fakeRestClient.addSuccessResponse(url, "SampleJson/group/CourseGroupCategories.json");
		List<GroupCategory> groupCategorys = groupCategoryReader.listGroupCategories(COURSE_ID);
		Assert.assertEquals("Expected that 4 group categories are in the course", 4, groupCategorys.size());
		GroupCategory first = groupCategorys.get(0);
		Assert.assertEquals("Expected id in object to match id in json", new Integer(123), first.getId());
		Assert.assertEquals("Expected name in object to match name in json", "Tutorial groups", first.getName());
	}

	@Test
	public void testGettingGroup() throws IOException {
		int groupId = 529;
		String url = baseUrl + "/api/v1/groups/" + groupId;
		fakeRestClient.addSuccessResponse(url, "SampleJson/group/Group.json");
		Optional<Group> groupFetcher = groupReader.getGroup(groupId);
		Assert.assertTrue(groupFetcher.isPresent());
		Group group = groupFetcher.get();
		Assert.assertEquals(Integer.valueOf(groupId), group.getId());
	}

	@Test
	public void testGettingGroupMembership() throws Exception {
		int groupId = 529;
		String url = baseUrl + "/api/v1/" + String.format("groups/%s/memberships", groupId);
		fakeRestClient.addSuccessResponse(url, "SampleJson/group/GroupMembership.json");
		List<GroupMembership> groupMemberships = groupMembershipReader.listGroupMembership(groupId);
		Assert.assertEquals("Expected that 5 group members in group", 5, groupMemberships.size());
		GroupMembership first = groupMemberships.get(0);
		Assert.assertEquals("Expected id in object to match id in json", new Integer(74), first.getId());
		Assert.assertEquals("Expected user id in object to match id in json", new Integer(42), first.getUserId());
	}

	/**
	 * Group Category
	 */
	/**
	 * TEST CUD OPS
	 */
	/*@Test
	public void testCreateGroupCategory() throws IOException {
		String url = baseUrl + "/api/v1/" + String.format("/group_categories/%s/groups", COURSE_ID);
		fakeRestClient.addSuccessResponse(url, "SampleJson/group/CreateGroupCategoryResponse.json");
		GroupCategory item = new GroupCategory();
		item.setName("TESTING-GroupCategory");
		item.setContextType("context_type");
		item.setCourseId(Integer.valueOf(COURSE_ID));

		Optional<GroupCategory> groupCategoryResult = groupCategoryWriter.createGroupCategory(COURSE_ID, item);
		GroupCategory returnedGroupCategory = groupCategoryResult.orElseThrow(AssertionFailedError::new);
		Assert.assertEquals(Integer.valueOf(9999), returnedGroupCategory.getId());
	}*/

	/*@Test(expected = NullPointerException.class)
	public void testCreateGroupCategoryNoName() throws IOException {
		GroupCategory item = new GroupCategory();
		//item.setName("NAME LEFT BLANK == NULL");
		item.setCourseId(Integer.parseInt(COURSE_ID));
		groupCategoryWriter.createGroupCategory(COURSE_ID, item);
	}*/

	@Test(expected = IllegalArgumentException.class)
	public void testCreateGroupCategoryNoCourseId() throws IOException {
		GroupCategory item = new GroupCategory();
		item.setName("COURSE ID LEFT BLANK");
		//item.setCourseId(Integer.parseInt(COURSE_ID));
		groupCategoryWriter.createGroupCategory(COURSE_ID, item);
	}

	/*@Test
	public void testUpdateGroupCategory() throws IOException {
		int id = 9999;
		String url = baseUrl + "/api/v1/group_categories/" + id;
		fakeRestClient.addSuccessResponse(url, "SampleJson/group/UpdateGroupCategoryResponse.json");
		GroupCategory groupCategory = new GroupCategory();
		groupCategory.setName("TESTING-UPDATED-GroupCategory");
		groupCategory.setId(id);
		groupCategory.setCourseId(Integer.valueOf(COURSE_ID));
		Optional<GroupCategory> updatedGroupCategoryOptional = groupCategoryWriter.updateGroupCategory(groupCategory);
		GroupCategory item = updatedGroupCategoryOptional.orElseThrow(AssertionFailedError::new);
		Assert.assertEquals("TESTING-UPDATED-GroupCategory", item.getName());
	}*/

	@Test
	public void testDeleteGroupCategory() throws IOException {
		int id = 9999;
		String url = baseUrl + "/api/v1/group_categories/" + id;
		fakeRestClient.addSuccessResponse(url, "SampleJson/group/DeleteGroupCategoryResponse.json");
		Optional<GroupCategory> deletedGroupCategoryOptional
				= groupCategoryWriter.deleteGroupCategory(String.valueOf(id));
		GroupCategory item = deletedGroupCategoryOptional.orElseThrow(AssertionFailedError::new);
	//	Assert.assertEquals("DELETED-DV-GC-TESTING", item.getName());
	}


	@Test
	public void testGettingGroupsInGroupCategory() throws Exception {
		int groupCategoryId = 9999;
		String url = baseUrl + "/api/v1/" + String.format("group_categories/%s/groups", groupCategoryId);
		fakeRestClient.addSuccessResponse(url, "SampleJson/group/GroupsInGroupCategory.json");
		List<Group> groups = groupReader.listGroupsInGroupCategory(groupCategoryId);
		Assert.assertEquals("Expected that 2 groups in group category", 2, groups.size());
		Group first = groups.get(0);
		Assert.assertEquals("Expected id in object to match id in json", new Integer(9919), first.getId());
		Assert.assertEquals("Expected name in object to match id in json", "XYZ_2019_SM5 - Tutorial 1", first.getName());
		Assert.assertEquals("Expected courseId in object to match id in json", new Integer(4), first.getCourseId());
	}

	/**
	 * Group
	 */
	/**
	 * TEST CUD OPS
	 * @throws java.io.IOException
	 */
	@Test
	public void testCreateGroup() throws IOException {
		int groupCategoryId = 35;
		String url = baseUrl + "/api/v1/" + String.format("group_categories/%s/groups", groupCategoryId);
		fakeRestClient.addSuccessResponse(url, "SampleJson/group/CreateGroupResponse.json");
		Group group = new Group();
		group.setName("TESTING-Group");
		group.setContextType("Course");
		group.setCourseId(Integer.valueOf(COURSE_ID));

		Optional<Group> returnGroup = groupWriter.createGroup(COURSE_ID, String.valueOf(groupCategoryId), group);
		Group returnedGroupCategory = returnGroup.orElseThrow(AssertionFailedError::new);
		Assert.assertEquals(Integer.valueOf(123456), returnedGroupCategory.getId());
		Assert.assertEquals("TESTING-Group", returnedGroupCategory.getName());
	}

	@Test(expected = NullPointerException.class)
	public void testCreateGroupNoName() throws IOException {
		int groupCategoryId = 35;
		Group item = new Group();
		//item.setName("NAME LEFT BLANK == NULL");
		item.setCourseId(Integer.parseInt(COURSE_ID));
		groupWriter.createGroup(COURSE_ID, String.valueOf(groupCategoryId), item);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateGroupNoCourseId() throws IOException {
		int groupCategoryId = 35;
		Group item = new Group();
		item.setName("COURSE ID LEFT BLANK");
		//item.setCourseId(Integer.parseInt(COURSE_ID));
		groupWriter.createGroup(COURSE_ID, String.valueOf(groupCategoryId), item);
	}

	@Test
	public void testUpdateGroup() throws IOException {
		int id = 123456;
		String url = baseUrl + "/api/v1/groups/" + id;
		fakeRestClient.addSuccessResponse(url, "SampleJson/group/UpdateGroupResponse.json");
		Group item = new Group();
		item.setName("TESTING-Group-Updated");
		item.setId(id);
		item.setCourseId(Integer.valueOf(COURSE_ID));
		Optional<Group> updatedGroupOptional = groupWriter.updateGroup(item);
		Group newGroup = updatedGroupOptional.orElseThrow(AssertionFailedError::new);
		Assert.assertEquals("TESTING-Group-Updated", newGroup.getName());
	}

	@Test
	public void testDeleteGroup() throws IOException {
		int id = 123456;
		String url = baseUrl + "/api/v1/groups/" + id;
		fakeRestClient.addSuccessResponse(url, "SampleJson/group/DeleteGroupResponse.json");
		Optional<Group> deletedGroupOptional
				= groupWriter.deleteGroup(String.valueOf(id));
		Group item = deletedGroupOptional.orElseThrow(AssertionFailedError::new);
		Assert.assertEquals("TESTING-Group-Deleted", item.getName());
	}

	/**
	 * Group Membership
	 */
	/**
	 * TEST CUD OPS
	 * @throws java.io.IOException
	 */
	@Test
	public void testCreateGroupMembership() throws IOException {
		final int groupId = 529;
		final int userId = 42;
		String url = baseUrl + "/api/v1/groups/" + groupId + "/memberships";
		fakeRestClient.addSuccessResponse(url, "SampleJson/group/CreateGroupMembership.json");
		GroupMembership groupMembership = new GroupMembership();
		groupMembership.setGroupId(groupId);
		groupMembership.setUserId(userId);

		Optional<GroupMembership> returnGroupMembershipOptional = groupMembershipWriter.enrollUserInGroup(groupMembership);
		GroupMembership returnedGroupMembership = returnGroupMembershipOptional.orElseThrow(AssertionFailedError::new);
		Assert.assertEquals(Integer.valueOf(8444), returnedGroupMembership.getId());
		Assert.assertEquals("invited", returnedGroupMembership.getWorkflowState());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateGroupMembershipNoUserId() throws IOException {
		final int groupId = 529;
		final int userId = 42;
		GroupMembership groupMembership = new GroupMembership();
		groupMembership.setGroupId(groupId);
//        groupMembership.setUserId(userId);
		groupMembershipWriter.enrollUserInGroup(groupMembership);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateGroupMembershipNoGroupId() throws IOException {
		final int groupId = 529;
		final int userId = 42;
		GroupMembership groupMembership = new GroupMembership();
		//   groupMembership.setGroupId(groupId);
		groupMembership.setUserId(userId);
		groupMembershipWriter.enrollUserInGroup(groupMembership);
	}

	@Test
	public void testUpdateGroupMembership() throws IOException {
		final int groupId = 529;
		int id = 8444;
		int userId = 42;
		String url = baseUrl + "/api/v1/groups/" + groupId + "/memberships/" + id;
		fakeRestClient.addSuccessResponse(url, "SampleJson/group/UpdateGroupMembership.json");
		GroupMembership groupMembership = new GroupMembership();
		groupMembership.setId(id);
		groupMembership.setGroupId(groupId);
		groupMembership.setUserId(userId);
		groupMembership.setWorkflowState("accepted");
		Optional<GroupMembership> returnGroupMembershipOptional = groupMembershipWriter.updateGroupMembership(groupMembership);
		GroupMembership returnedGroupMembership = returnGroupMembershipOptional.orElseThrow(AssertionFailedError::new);
		Assert.assertEquals(Integer.valueOf(8444), returnedGroupMembership.getId());
		Assert.assertEquals("accepted", returnedGroupMembership.getWorkflowState());
	}

	@Test
	public void testDeleteGroupMembership() throws IOException {
		final int groupId = 529;
		int id = 8444;
		String url = baseUrl + "/api/v1/groups/" + groupId + "/memberships/" + id;
		fakeRestClient.addSuccessResponse(url, "SampleJson/group/DeleteGroupMembership.json");
		GroupMembership groupMembership = new GroupMembership();
		groupMembership.setId(id);
		groupMembership.setWorkflowState("accepted");
		boolean result = groupMembershipWriter.dropGroupMembership(String.valueOf(id), String.valueOf(groupId));
		Assert.assertTrue(result);
	}
}

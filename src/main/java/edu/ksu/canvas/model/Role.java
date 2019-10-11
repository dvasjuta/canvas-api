package edu.ksu.canvas.model;

import java.io.Serializable;

import edu.ksu.canvas.annotation.CanvasObject;
import java.util.Map;

/**
 * Class to represent Canvas roles. See
 * <a href="https://canvas.instructure.com/doc/api/roles.html#Role">Roles</a>
 * documentation.
 */
@CanvasObject(postKey = "role")
public class Role extends BaseCanvasModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String label;
	private String baseRoleType;
	private transient Map<String, Role.PermissionBreakdown> permissions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getBaseRoleType() {
		return baseRoleType;
	}

	public void setBaseRoleType(String baseRoleType) {
		this.baseRoleType = baseRoleType;
	}

	public Map<String, PermissionBreakdown> getPermissions() {
		return permissions;
	}

	public void setPermissions(Map<String, PermissionBreakdown> permissions) {
		this.permissions = permissions;
	}

	public class PermissionBreakdown {

		private Boolean enabled;
		private Boolean locked;
		private Boolean readonly;
		private Boolean explicit;

		public Boolean getEnabled() {
			return enabled;
		}

		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}

		public Boolean getLocked() {
			return locked;
		}

		public void setLocked(Boolean locked) {
			this.locked = locked;
		}

		public Boolean getReadonly() {
			return readonly;
		}

		public void setReadonly(Boolean readonly) {
			this.readonly = readonly;
		}

		public Boolean getExplicit() {
			return explicit;
		}

		public void setExplicit(Boolean explicit) {
			this.explicit = explicit;
		}

		@Override
		public String toString() {
			return "PermissionBreakdown{" + "enabled=" + enabled + ", locked=" + locked + ", readonly=" + readonly + ", explicit=" + explicit + '}';
		}

	}
}

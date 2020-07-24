package edu.ksu.canvas.requestOptions;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CreateCourseContentMigrationOptions extends BaseOptions {

    /*public enum MigrationType {
        course_copy_importer;
        // Only allow course copy for now.
        //CANVAS_CARTRIDGE_IMPORTER, COMMON_CARTRIDGE_IMPORTER,
        //ZIP_FILE_IMPORTER, QTI_CONVERTER, MOODLE_CONVERTER;

        @Override
        public String toString() { return name().toLowerCase(); }

    }*/

	public enum Select {
		@SerializedName("folders")
		FOLDERS,
		@SerializedName("files")
		FILES,
		@SerializedName("attachments")
		ATTACHMENTS,
		@SerializedName("quizzes")
		QUIZZES,
		@SerializedName("assignments")
		ASSIGNMENTS,
		@SerializedName("announcements")
		ANNOUNCEMENTS,
		@SerializedName("calendar_events")
		CALENDAR_EVENTS,
		@SerializedName("discussion_topics")
		DISCUSSION_TOPICS,
		@SerializedName("modules")
		MODULES,
		@SerializedName("module_items")
		MODULE_ITEMS,
		@SerializedName("pages")
		PAGES,
		@SerializedName("rubrics")
		RUBRICS;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

    //private final String destinationCourseId;

    //private final String sourceCourseId;

    //private final MigrationType migrationType;
    
	public CreateCourseContentMigrationOptions() { }
	/*public CreateCourseContentMigrationOptions(String destinationCourseId, String sourceCourseId, MigrationType migrationType) {
        this.destinationCourseId = destinationCourseId;
        this.sourceCourseId = sourceCourseId;
        this.migrationType = migrationType;
        addSingleItem("settings[source_course_id]", sourceCourseId);
        addSingleItem("migration_type", migrationType.toString().toLowerCase());
    }*/

    /*public String getDestinationCourseId() {
        return destinationCourseId;
    }

    public String getSourceCourseId() {
        return sourceCourseId;
    }

    public MigrationType getMigrationType() {
        return migrationType;
    }*/

    /**
     * If set, only the selected types will be included in the migration.
     * @param selectTypes List of migration types to include
     * @return This object to allow adding more options
     */
    public CreateCourseContentMigrationOptions withSelect(List<Select> selectTypes) {
        addEnumList("select[]", selectTypes);
        return this;
    }

    /**
     * If set, perform a selective import instead of importing all content.
     * @param selectiveImport if selective import should be used
     * @return This object to allow adding more options
     */
	public CreateCourseContentMigrationOptions selectiveImport(Boolean selectiveImport) {
        addSingleItem("selective_import", String.valueOf(selectiveImport));
        return this;
    }
}
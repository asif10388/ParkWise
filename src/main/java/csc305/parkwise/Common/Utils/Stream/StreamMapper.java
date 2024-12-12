package csc305.parkwise.Common.Utils.Stream;

import java.util.EnumMap;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;

public class StreamMapper {
    private static final EnumMap<ObjectStreams, String> objectStreams = new EnumMap<>(ObjectStreams.class);

    public StreamMapper() {
        makeObjectStreams();
    }

    public static void makeObjectStreams() {
        String basePath = "src/main/java/csc305/parkwise/Common/Streams/";
        objectStreams.put(ObjectStreams.UserObjects, basePath + "UserObjects.bin");
        objectStreams.put(ObjectStreams.StaffObjects, basePath + "StaffObjects.bin");
        objectStreams.put(ObjectStreams.BudgetObjects, basePath + "BudgetObjects.bin");
        objectStreams.put(ObjectStreams.CampsiteObjects, basePath + "CampsiteObjects.bin");
        objectStreams.put(ObjectStreams.EquipmentObjects, basePath + "EquipmentObjects.bin");
        objectStreams.put(ObjectStreams.RegulationObjects, basePath + "RegulationObjects.bin");
        objectStreams.put(ObjectStreams.AnnouncementObjects, basePath + "AnnouncementObjects.bin");
        objectStreams.put(ObjectStreams.CampsiteBookingObjects, basePath + "CampsiteBookingObjects.bin");
        objectStreams.put(ObjectStreams.PerformanceReviewObjects, basePath + "PerformanceReviewObjects.bin");
        objectStreams.put(ObjectStreams.RestockSuppliesRequestObjects, basePath + "RestockSuppliesRequestObjects.bin");
        objectStreams.put(ObjectStreams.CampgroundMaintenanceRequestObjects, basePath + "CampgroundMaintenanceRequestObjects.bin");
    }

    public String getObjectStream(ObjectStreams stream){
        return objectStreams.get(stream);
    }
}

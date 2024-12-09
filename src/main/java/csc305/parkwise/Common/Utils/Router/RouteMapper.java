package csc305.parkwise.Common.Utils.Router;

import csc305.parkwise.Common.Utils.Router.RoutesEnum.CampgroundManagerRoutes;
import csc305.parkwise.Common.Utils.Router.RoutesEnum.ParkDirectorRoutes;

import java.util.EnumMap;

public class RouteMapper {
    private static final EnumMap<ParkDirectorRoutes, String> parkDirectorRoutes = new EnumMap<>(ParkDirectorRoutes.class);
    private static final EnumMap<CampgroundManagerRoutes, String> campgroundManagerRoutes = new EnumMap<>(CampgroundManagerRoutes.class);

    public RouteMapper() {
        makeParkDirectorRoutes();
        makeCampgroundManagerRoutes();
    }

    public static void makeCampgroundManagerRoutes() {
        campgroundManagerRoutes.put(CampgroundManagerRoutes.AssignRangerView, "Users/Asif/CampgroundManager/assign-ranger-view.fxml");
        campgroundManagerRoutes.put(CampgroundManagerRoutes.CamperEntriesView, "Users/Asif/CampgroundManager/camper-entries-view.fxml.fxml");
        campgroundManagerRoutes.put(CampgroundManagerRoutes.CampsiteAvailabilityView, "Users/Asif/CampgroundManager/campsite-availability-view.fxml");
        campgroundManagerRoutes.put(CampgroundManagerRoutes.CMDashboardView, "Users/Asif/CampgroundManager/cm-dashboard.fxml");
        campgroundManagerRoutes.put(CampgroundManagerRoutes.MaintenanceRequestsView, "Users/Asif/CampgroundManager/maintenance-requests-view.fxml");
        campgroundManagerRoutes.put(CampgroundManagerRoutes.OccupancyReportsView, "Users/Asif/CampgroundManager/occupancy-reports-view.fxml");
        campgroundManagerRoutes.put(CampgroundManagerRoutes.OnsiteRegistrationsView, "Users/Asif/CampgroundManager/onsite-registrations-view.fxml");
        campgroundManagerRoutes.put(CampgroundManagerRoutes.RentalEquipmentView, "Users/Asif/CampgroundManager/rental-equipment-view.fxml");
    }

    public static void makeParkDirectorRoutes() {
        parkDirectorRoutes.put(ParkDirectorRoutes.AnnouncementsView, "Users/Asif/ParkDirector/announcements-view.fxml");
        parkDirectorRoutes.put(ParkDirectorRoutes.BudgetsView, "Users/Asif/ParkDirector/budgets-view.fxml");
        parkDirectorRoutes.put(ParkDirectorRoutes.FinancialReportsView, "Users/Asif/ParkDirector/financial-reports-view.fxml");
        parkDirectorRoutes.put(ParkDirectorRoutes.PDDashboardView, "Users/Asif/ParkDirector/pd-dashboard.fxml");
        parkDirectorRoutes.put(ParkDirectorRoutes.MaintenanceRequestsView, "Users/Asif/ParkDirector/maintenance-requests-view.fxml");
        parkDirectorRoutes.put(ParkDirectorRoutes.PerformanceReviewsView, "Users/Asif/ParkDirector/performance-reviews-view.fxml");
        parkDirectorRoutes.put(ParkDirectorRoutes.RegulationsView, "Users/Asif/ParkDirector/regulations-view.fxml");
        parkDirectorRoutes.put(ParkDirectorRoutes.ResearchProposalsView, "Users/Asif/ParkDirector/research-proposals-view.fxml");
        parkDirectorRoutes.put(ParkDirectorRoutes.StaffAccountsView, "Users/Asif/ParkDirector/staff-accounts-view.fxml");
    }

    public String getCampgroundManagerRoute(CampgroundManagerRoutes route){
        return campgroundManagerRoutes.get(route);
    }

    public String getParkDirectorRoute(ParkDirectorRoutes route){
        return parkDirectorRoutes.get(route);
    }
}
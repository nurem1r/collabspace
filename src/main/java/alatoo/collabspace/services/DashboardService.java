package alatoo.collabspace.services;

import alatoo.collabspace.dto.DashboardStatsDto;
import alatoo.collabspace. dto.UserDashboardDto;

public interface DashboardService {
    DashboardStatsDto getGlobalStats();
    UserDashboardDto getUserDashboard(Long userId);
}
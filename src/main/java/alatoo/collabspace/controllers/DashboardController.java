package alatoo.collabspace.controllers;

import alatoo.collabspace.dto.DashboardStatsDto;
import alatoo.collabspace. dto.UserDashboardDto;
import alatoo.collabspace.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org. springframework.http.ResponseEntity;
import org. springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDto> getGlobalStats() {
        return ResponseEntity.ok(dashboardService. getGlobalStats());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDashboardDto> getUserDashboard(@PathVariable Long userId) {
        return ResponseEntity.ok(dashboardService.getUserDashboard(userId));
    }
}
package org.botnicholas.projects.democron.controllers;

import org.botnicholas.projects.democron.controllers.models.RequestWrapperDTO;
import org.botnicholas.projects.democron.controllers.models.SchedulingRequest;
import org.botnicholas.projects.democron.services.CronSchedulerService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleJobController {
    private final CronSchedulerService cronScheduler;

    @Autowired
    public ScheduleJobController(final CronSchedulerService cronScheduler) {
        this.cronScheduler = cronScheduler;
    }

    @GetMapping
    public String hello() {
        return "Hello World";
    }

    /**
     * {
     *     "parameters": [
     *         {
     *             "key":"other",
     *             "values": ["var_1", "var_2"]
     *         },
     *         {
     *             "key":"other_object",
     *             "values": ["{'p':'v';}"]
     *         },
     *         {
     *             "key":"occurrence",
     *             "values": ["DAILY"]
     *         },
     *         {
     *             "key":"occurrenceDetails",
     *             "values": [
     *                 {
     *                      "time": "10:30",
     *                      "day": 1,
     *                      "zone": "Europe/Paris"
     *                 }
     *             ]
     *         }
     *     ]
     * }
     */
    @PostMapping("/test-complex-object")
    public ResponseEntity<String> testComplexObject(@RequestBody final RequestWrapperDTO wrapper) {
        System.out.println(wrapper);
        return ResponseEntity.ok("Received");
    }

    @PostMapping("/schedule/every/hour")
    public ResponseEntity<String> scheduleEveryHour(@RequestBody SchedulingRequest timeStamp) throws SchedulerException {
        cronScheduler.scheduleCron(timeStamp);
        return ResponseEntity.ok("Schedule Every " + timeStamp);
    }
}

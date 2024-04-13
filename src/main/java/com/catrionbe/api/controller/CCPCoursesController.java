package com.catrionbe.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catrionbe.api.model.CourseUpdateRequest;
import com.catrionbe.api.model.UserIdRequest;
import com.catrionbe.api.service.CCPCourseService;
import com.catrionbe.api.service.CCPNewsService;

@RestController
@CrossOrigin
public class CCPCoursesController {

	@Autowired
	private CCPCourseService objCCPCourseService;

	// Mark Courses comepleted when user completed the course
	@RequestMapping(value = "/markcoursecomplete", method = RequestMethod.POST)
	public ResponseEntity<?> saveCourse(@RequestBody CourseUpdateRequest courseUpdateRequest) throws Exception {
		return ResponseEntity.ok(objCCPCourseService.save(courseUpdateRequest));
	}

	@RequestMapping(value = "/fetchmaxscreenId", method = RequestMethod.POST)
	public ResponseEntity<?> fetchMaxScreenId(@RequestBody UserIdRequest userIdRequest) throws Exception {
		return ResponseEntity.ok((objCCPCourseService.fetchMaxScreenId(userIdRequest)));
	}

	@RequestMapping(value = "/completedwithworkemail", method = RequestMethod.GET)
	public ResponseEntity<?> completedWithWorkEmail() throws Exception {
		return ResponseEntity.ok((objCCPCourseService.completedWithWorkEmail()));
	}

	@RequestMapping(value = "/completedccbasic", method = RequestMethod.GET)
	public ResponseEntity<?> completedCCPBasic() throws Exception {
		return ResponseEntity.ok((objCCPCourseService.completedCCPBasic()));
	}

	@RequestMapping(value = "/completedwithmobile", method = RequestMethod.GET)
	public ResponseEntity<?> completedWithMobilePhone() throws Exception {
		return ResponseEntity.ok((objCCPCourseService.completedWithMobilePhone()));
	}

	@RequestMapping(value = "/remainder", method = RequestMethod.GET)
	public ResponseEntity<?> reminder() throws Exception {
		return ResponseEntity.ok((objCCPCourseService.reminder()));
	}

	@RequestMapping(value = "/loginbutstarted", method = RequestMethod.GET)
	public ResponseEntity<?> loginButStarted() throws Exception {
		return ResponseEntity.ok((objCCPCourseService.loginButStarted()));
	}

	@RequestMapping(value = "/startedbutnotcompleted", method = RequestMethod.GET)
	public ResponseEntity<?> startedButNotCompleted() throws Exception {
		return ResponseEntity.ok((objCCPCourseService.startedButNotCompleted()));
	}

}

var app = angular.module('studentApp', []);
app.controller('studentCtrl', function($scope) {
	// Providing default values
	$scope.studentName = "Enter Name here";
	$scope.sub1Mark = 0;
	$scope.sub2Mark = 0;
	$scope.sub3Mark = 0;
	// Initializing student list variable
	$scope.studentList = []

	// save function to save the value
	$scope.save = function() {
		var obj = {
			"studentName" : $scope.studentName,
			"sub1Mark" : $scope.sub1Mark,
			"sub2Mark" : $scope.sub2Mark,
			"sub3Mark" : $scope.sub3Mark
		}

		if (!$scope.studentName) {
			alert("Please enter the student's name");
		} else if (!$scope.sub1Mark) {
			alert("Please enter mark for Subject 1");
		} else if (!$scope.sub2Mark) {
			alert("Please enter mark for Subject 2");
		} else if (!$scope.sub3Mark) {
			alert("Please enter mark for Subject 3");
		} else {
			$scope.studentList.push(obj);
			$scope.studentName = "Enter Name here";
			$scope.sub1Mark = 0;
			$scope.sub2Mark = 0;
			$scope.sub3Mark = 0;
			alert("Student Details are saved Successfully!!!");
		}
	}

});
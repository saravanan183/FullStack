var studentModule = angular.module("studentModule",[]);

studentModule.directive('slider', function() {
    var directive = {};
    directive.restrict = 'E';
    directive.template = "<div id='slider'></div>";
    directive.controller=function($scope) 
    {    
    	$( "#slider" ).slider({
    	      range: "max",
    	      min:5,
    	      max:70,
    	      value: 1,
    	      slide: function( event, ui ) {
    	        $( "#age" ).val( ui.value );
    	      }
    	    });
    	$( "#age" ).val( $( "#slider" ).slider( "value" ) );   
    	
    }
    return directive;
 });


studentModule.controller("addStudentController", function($scope,  $http){
  $http.get("http://localhost:8080/StudentService/student/getNextStudentID").then(function (response) {
	      $scope.nextStudentID = response.data.maxID;
	  });
  
   $scope.update = function(student) {
	    $scope.student.id = $scope.nextStudentID;
    	$scope.master = angular.copy(student);
        $http.post("http://localhost:8080/StudentService/student/addStudent",
        		$scope.master)
                .then(function (response) {
                	$scope.statusMsg = "Student's Data Saved Successfully";                 	
                    //window.location.href="#viewStudents";
                });
    }
 
});

studentModule.controller("viewStudentController", function($scope,  $http){
   $http.get("http://localhost:8080/StudentService/student/getAllStudents").then(function (response) {
	      $scope.student = response.data;
	  });
    
    $scope.deleteStudent = function(item){
    	$scope.data1 =  item;
    	$http.get("http://localhost:8080/StudentService/student/deleteStudent/"+$scope.data1).then(function (response) {
    	  });

    		$http.get("http://localhost:8080/StudentService/student/getAllStudents").then(function (response) {
    			  $scope.statusMsg = "Student's Data deleted Successfully";    
    		      $scope.student = response.data;
    		  });
    	}
    
    $scope.edit = function(item){
    	$scope.data1 =  item;
    	$http.get("http://localhost:8080/StudentService/student/getStudentById/"+$scope.data1).then(function (response) {
    		$scope.student1 = response.data;
    	  });
    	  
    	}
    $scope.update = function(student1) {
    	$scope.master = angular.copy(student1);
        $http.post("http://localhost:8080/StudentService/student/updateStudent",
        		$scope.master)
                .then(function (response) {
                	$scope.student1 = "";
                	$http.get("http://localhost:8080/StudentService/student/getAllStudents").then(function (response) {
                		$scope.statusMsg = "Student's Data Saved Successfully";    
          		      	$scope.student = response.data;
          		  });
                });    
    }  
    
});
var studentModule = angular.module("studentModule",[]);

studentModule.directive('slider', function() {
    var directive = {};
    directive.restrict = 'E';
    directive.template = "<div id='slider'></div>";
    
    directive.scope = {
       data : "="
    },
    directive.controller=function($scope) { 
    	alert($( "#age" ).val);
    	$( "#slider" ).slider({
    	      range: "max",
    	      min: 10,
    	      max: 45,
    	      value: $( "#age" ).val,
    	      slide: function( event, ui ) {
    	        $( "#age" ).val( ui.value );
    	      }
    	    });
    	    $( "#age" ).val( $( "#slider" ).slider( "value" ) );    	
    	console.log('Testing',$scope);
    }
    return directive;
 });


studentModule.controller("addStudentController", function($scope, studentFactory, studentService, $http){
   
    
	
   $scope.update = function(student) {
    	$scope.master = angular.copy(student);
        //studentFactory.update($scope.master);   	
    	
        console.log($scope.master);
        $http.post("http://10.172.121.112:9080/StudentService/student/addStudent",
        		$scope.master)
                .then(function (response) {
                    //console.log(response);
                	$scope.student = {};
                	// angular.copy({},addStudentForm); 
                	$scope.message = "Data Saved Successfully";
                    //window.location.href="#viewStudents";
                });
    	
        
       // alert("Data Saved Successfully");
        
        
    }  
});

studentModule.controller("viewStudentController", function($scope, studentFactory, studentService, $http){
    //$scope.student = studentFactory.list();
   $http.get("http://10.172.121.112:9080/StudentService/student/getAllStudents").then(function (response) {
	      $scope.student = response.data;
	      $scope.message = "";
	  });
    
    $scope.deleteStudent = function(item){
    	$scope.data1 =  item;
    	$http.get("http://10.172.121.112:9080/StudentService/student/deleteStudent/"+$scope.data1).then(function (response) {
    		$http.get("http://10.172.121.112:9080/StudentService/student/getAllStudents").then(function (response) {
  		      $scope.student = response.data;
  		      $scope.message = "Data Deleted Successfully";
  		  });
    	  });

    		
    	  
    	}
    
    $scope.edit = function(item){
    	$scope.data1 =  item;
    	$scope.message = "";
    	$http.get("http://10.172.121.112:9080/StudentService/student/getStudentById/"+$scope.data1).then(function (response) {
    		console.log(response.data);
    		$scope.student1 = response.data;
    //		window.location.href="#addStudents";
    	  });
    	  
    	}
    $scope.update = function(student1) {
    	$scope.master = angular.copy(student1);
        //studentFactory.update($scope.master);   	
    	
        console.log($scope.master);
        $http.post("http://10.172.121.112:9080/StudentService/student/updateStudent",
        		$scope.master)
                .then(function (response) {
                    //console.log(response);
                    //window.location.href="#viewStudents";
                	
                	$scope.student1 = "";
                	$http.get("http://10.172.121.112:9080/StudentService/student/getAllStudents").then(function (response) {
          		      $scope.student = response.data;
          		      $scope.message = "Data Saved Successfully";
          		  });
                });
    	
        
       // alert("Data Saved Successfully");
        
        
    }  
    
});

studentModule.factory("studentFactory", function(){
    

        var studentList = [];
    
        var thisService = {};
        
        thisService.update = function(student){
        	console.log('Student in factory'+student);
        	studentList.push(student);
        	console.log('studentList'+ studentList);
        } 
        
        thisService.list = function(){
           // return studentList;
        	
        	$http({
        	    method : "GET",
        	    url :"http://10.172.121.112:9080/StudentService/student/getAllStudents"
        	  }).then(function mySuccess(response) {        	  
        	      return response.data;
        	    }, function myError(response) {
        	      console.log(response.statusText);
        	  });
        	
        }
        
        return thisService;

});

studentModule.service("studentService", function(){    

        var studentList = [];
        
        this.update = function(student){
        	console.log('Student in studentservice'+student);
        	console.log(student);
        	studentList.push(student);
        	console.log('studentList'+ studentList);
        } 
        
        this.list = function(){
            return studentList;
        }

});


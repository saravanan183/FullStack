var studentModule = angular.module("studentModule",[]);

studentModule.directive('slider', function() {
    var directive = {};
    directive.restrict = 'E';
    directive.template = "<div id='slider'></div>";
    
    directive.scope = {
       data : "="
    },
    directive.controller=function($scope) {
        console.log("2==>"+$scope);
    	$( "#slider" ).slider({
    	      range: "max",
    	      min: 10,
    	      max: 45,
    	      value: 2,
    	      slide: function( event, ui ) {
    	        $( "#age" ).val( ui.value );
    	        //$scope.student.age =  ui.value;
    	        //alert( ui.value);
    	      }
    	    });
    	    $( "#age" ).val( $( "#slider" ).slider( "value" ) );  
    	    
    	    //$scope.student.age = $( "#slider" ).slider( "value" );
    	
    }
    return directive;
 });


studentModule.controller("addStudentController", function($scope, studentFactory, studentService, $http){
   
    
	
   $scope.update = function(student) {
    	$scope.master = angular.copy(student);
      
        console.log($scope.master);
        $http.post("http://localhost:8080/StudentServiceBoot/addStudent",
        		$scope.master)
                .then(function (response) {
                    $scope.message = "Data Saved Successfully";
                    window.location.href="#viewStudents";
                });
    
        
    }  
});

studentModule.controller("viewStudentController", function($scope, studentFactory, studentService, $http){
    //$scope.student = studentFactory.list();
   $http.get("http://localhost:8080/StudentServiceBoot/getAllStudents").then(function (response) {
	      $scope.student = response.data;
	      $scope.message = "";
	  });
    
    $scope.deleteStudent = function(item){
    	$scope.data1 =  item;
    	$http.get("http://localhost:8080/StudentServiceBoot/deleteStudent/"+$scope.data1).then(function (response) {
    		$http.get("http://localhost:8080/StudentServiceBoot/getAllStudents").then(function (response) {
  		      $scope.student = response.data;
  		      $scope.message = "Data Deleted Successfully";
  		  });
    	  });

    		
    	  
    	}
    
    $scope.edit = function(item){
    	$scope.data1 =  item;
    	$scope.message = "";
    	$http.get("http://localhost:8080/StudentServiceBoot/getStudentById/"+$scope.data1).then(function (response) {
    		console.log(response.data);
    		$scope.student1 = response.data;
    	});
    	  
    	}
    $scope.update = function(student1) {
    	$scope.master = angular.copy(student1);
        
        console.log($scope.master);
        $http.post("http://localhost:8080/StudentServiceBoot/updateStudent",
        		$scope.master)
                .then(function (response) {
                    $scope.student1 = "";
                	$http.get("http://localhost:8080/StudentServiceBoot/getAllStudents").then(function (response) {
          		      $scope.student = response.data;
          		      $scope.message = "Data Saved Successfully";
          		  });
                });
                 
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
        	    url :"http://localhost:8080/StudentServiceBoot/getAllStudents"
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


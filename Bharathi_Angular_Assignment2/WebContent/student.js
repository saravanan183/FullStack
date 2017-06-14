var studentModule = angular.module("studentModule",[]);

studentModule.controller("addStudentController", function($scope, studentFactory, studentService){
    $scope.master = {};
   $scope.update = function(student) {
    	$scope.master = angular.copy(student);
        studentFactory.update($scope.master);
        alert("Data Saved Successfully");
        
    }  
});

studentModule.controller("viewStudentController", function($scope, studentFactory, studentService){
    $scope.student = studentFactory.list();
    
    $scope.deleteStudent = function(item){
    	angular.forEach($scope.student, function(value, key) {
    		  console.log(key + ': ' + value.id);
    		  if(value.id == item){
    			  $scope.student.splice(key, 1);
    		  }
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
            return studentList;
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

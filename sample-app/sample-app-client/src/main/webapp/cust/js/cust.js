'use strict';

var app = angular.module('cust', ['ngRoute']);

app.constant("config", {
	"base":"http://localhost:8080/sample-app-client"
});

app.controller('loginController', function($rootScope, $scope, $location, $http, config) {
	$scope.username = '';
	$scope.password = '';
	$scope.login = function() {
		$rootScope.error = '';
    	$scope.dataLoading = true;
    	$http({
    	    url: config.base + '/cust/login', 
    	    method: "POST",
    	    params: {username:$scope.username, password: $scope.password}
    	 })
    	 .then(function () {
	         	$rootScope.error = 'Successful login';
	        	$rootScope.authenticated = true;
	        	$scope.dataLoading = false;
	            $location.path('/');
		  }, function () {
              	$rootScope.error = "Login failed";
                $rootScope.authenticated = false;
                $scope.dataLoading = false;
	            $location.path('/');
		  });
    };
    
    $rootScope.logout = function() {
    	$rootScope.error = '';
    	$http({
    	    url: config.base + '/cust/logout', 
    	    method: "POST",
    	    params: {}
    	 })
    	 .then(function () {
	         	$rootScope.error = 'Successful logout';
	        	$rootScope.authenticated = false;
	            $location.path('/');
		  }, function (response) {
              	$rootScope.error = response.error;
                $rootScope.authenticated = true;
	            $location.path('/');
		  });
    };    
});

app.controller('navbarController', function($scope, $location) {
	$scope.getClass = function(path) {
		return ($location.path().substr(0, path.length) === path);
	}
});

app.controller('listController', function ($rootScope, $scope, services) {
	$rootScope.error = '';
    services.getCustomers().then(function(response){
        $scope.customers = response.data;
    });
});

app.controller('editController', function ($rootScope, $scope, $location, $routeParams, services) {
    var customerID = ($routeParams.customerID) ? parseInt($routeParams.customerID) : 0;
    
	$rootScope.error = '';
    $rootScope.title = (customerID > 0) ? 'Edit Customer' : 'Add Customer';
    $scope.buttonText = (customerID > 0) ? 'Update Customer' : 'Add New Customer';
    
    var original = {};
    $scope.customer = {};
    
    if (customerID > 0) {
        services.getCustomer(customerID).then(function(response){
        	original = response.data;
        	$scope.customer = angular.copy(original);            
        });    	
    } 
	
	$scope.isClean = function() {
	  return angular.equals(original, $scope.customer);
	}
	
	$scope.deleteCustomer = function(customer) {
	  if(confirm("Are you sure to delete customer number: "+$scope.customer.id)) {
		  services.deleteCustomer(customer.id).then(function(){
			  $location.path('/customers');
		  });	  
	  }
	};
	
	$scope.saveCustomer = function(customer) {
	  services.saveCustomer(customer).then(function(){
		  $location.path('/customers');
	  });	  
    };
});

app.factory("services", function($http, config) {
	    var obj = {};
	    obj.getCustomers = function(){
	        return $http.get(config.base + '/customers');
	    }
	    obj.getCustomer = function(id){
	        return $http.get(config.base + '/customer/' + id);
	    }

	    obj.saveCustomer = function (customer) {
		    return $http.post(config.base + '/customer', customer).then(function (results) {
		        return results;
		    });
		};

		obj.deleteCustomer = function (id) {
		    return $http.delete(config.base + '/customer/' + id).then(function (status) {
		        return status.data;
		    });
		};

	    return obj;   
	});

app.config(function($routeProvider) {
	$routeProvider.when('/', {
		title : 'Welcome',
		templateUrl : 'default.html'
	}).when('/customers', {
		title : 'Customers',
		templateUrl : 'customers.html',
		controller : 'listController'
	}).when('/login', {
		title : 'Login',
		templateUrl : 'login.html',
		controller : 'loginController'
	}).when('/edit-customer/:customerID', {
        title: 'Edit Customers',
        templateUrl: 'edit-customer.html',
        controller: 'editController',
    }).otherwise({
		redirectTo : '/'
	});
});

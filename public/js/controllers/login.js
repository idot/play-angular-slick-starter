define(['./module'], function (controllers) {
    'use strict';
    controllers.controller('Login', ['$scope', '$stateParams', 'userService', 'Restangular', function ($scope, $stateParams, userService, Restangular) {
	    $scope.stateParams = $stateParams;

	    $scope.username = "";
	    $scope.password = "";

	    $scope.login = function(){
	            var credentials = { 'username': $scope.username, 'password': $scope.password };
	            Restangular.all("api/login").post(credentials).then(
	               function(auth){
					   userService.state.setLoggedInUser(auth.user, auth["AUTH-TOKEN"]);
	               }
			   );
			   
		}; 
    }]);
	
});
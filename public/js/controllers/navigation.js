define(['./module'], function (controllers) {
    'use strict';
    controllers.controller('Navigation', ['$scope', 'userService', function ($scope, userService) {
		$scope.userService = userService;
    }]);
});
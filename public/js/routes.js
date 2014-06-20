define(['./app'], function (app) {
    'use strict';
    return app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/view1");
		
		$stateProvider.state('view1', {
            url: '/view1',
			templateUrl: 'partials/partial1.html',
            controller: 'MyCtrl1'
        }) 
		.state('view2', {
			url: '/view2',
            templateUrl: 'partials/partial2.html',
            controller: 'MyCtrl2'
        })
		.state('view3', {
			url: '/view3',
		    templateUrl: 'partials/partial3.html',
			controller: 'MyCtrl3'			
		})
		.state('login', {
		    url: '/login',
			templateUrl: 'partials/login.html',
			controller: 'Login'	
		})
		.state('logout', {
		    url: "/logout",
		    onEnter: function($state, userService) {
		          userService.state.logout();
		          $state.transitionTo("view1");
		    }
		})
		;
    }]);
});
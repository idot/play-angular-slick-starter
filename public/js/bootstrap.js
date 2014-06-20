
require(['d3'], function(d3){
	window.d3 = d3;	
});


/**
* bootstraps angular onto the window.document node
* NOTE: the ng-app attribute should not be on the index.html when using ng.bootstrap
*/
define([
    'require',
    'angular',
    'app',
    'routes'
], function (require, ng, app) {
    'use strict';

/*
* place operations that need to initialize prior to app start here
* using the `run` function on the top-level module
*/
	app.run(['Restangular', 'toaster', 'userService', 
	   function( Restangular, toaster, userService){
            
			Restangular.setErrorInterceptor(function(response, deferred, responseHandler) {
                 // if(response.status === 403) {
	             // refreshAccesstoken().then(function() {
	             // Repeat the request and then call the handlers the usual way.
	             //       $http(response.config).then(responseHandler, deferred.reject);
	             // Be aware that no request interceptors are called this way.
                //     });
                //      return false; // error handled
                //  }
                 var errors = _.map(response.data, function(value, key){ return key + " " +value; });
                 var errorsum = errors.join("\n");
                 toaster.pop('error', "application error: "+response.statusText, errorsum);
	             console.log("custom error handler: "+response.status+" "+response.data);
	                   return true; // error not handled
	             });
		 
		     /**
			 * if its a new tab, reauthenticate by cookie
			 **/
	         userService.state.reauthenticate();	 
		 
	
	
	}]);
       

    require(['domReady!'], function (document) {
        ng.bootstrap(document, ['app']);
    });
});
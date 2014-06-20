/**
* table helper for ng-table
* stores user state, cookie auth etc..
*   https://variadic.me/posts/2013-10-15-share-state-between-controllers-in-angularjs.html
*   angular.module('controllers', ['services'])
*     .factory('MainCtrl', function ($scope, state) {
*       $scope.state = state.state;
*
*      $scope.$watch('state', function (newVal, oldVal) {
*        // your code here
*      });
*    });
*
**/
define(['./module'], function (services) {
    'use strict';
    services.factory('userService', function($cookies, Restangular){
	    function UserState(){
			
			this.loggedInUser = {};
            this.authtoken = "";
			
			this.isLoggedIn = function(){
				return typeof this.authtoken !== "undefined" && this.authtoken != "";
			}
			
			this.logout = function(){
			   this.authtoken = "";
			   this.user = {};	
			   delete $cookies["AUTH-TOKEN"];	
			   Restangular.setDefaultHeaders();
			};	  	
			
			/**
			* update user calls this function without auth
			* after login it is called with auth
			**/			
     	   	this.setLoggedInUser = function(user, auth){
		        this.loggedInUser = user;
				if( typeof auth !== "undefined" ) {
	               this.authtoken = auth;
	               Restangular.setDefaultHeaders({'X-AUTH-TOKEN': auth});
			    }
			};
						
			/**
            * opening a new window looses all info in the new window
            * we grab the cookie containing the auth token and reload the user
            * if cookie not there logout => reset user to default
			* a new browser window has no auth-token set
			* we check for cookie and set auth-token again
			* we also populate the user object with the data
			*
			**/
			this.reauthenticate = function(){
			    if(! this.isLoggedIn() ){
			         var auth = $cookies["AUTH-TOKEN"];
			         if(typeof auth !== "undefined"){
			             this.authtoken = auth;
			             Restangular.setDefaultHeaders({'X-AUTH-TOKEN': auth});
						 var outer = this;
			             Restangular.one('api/ping').get().then(function(userWithEmail){
			                  outer.loggedInUser = userWithEmail;
			             });
			         }else{
						 this.logout();
			         }
			    } 
			};
	    };
		
			
		var state = new UserState();
				
		return {
		    state: state,     
		};

   })
});
 
 


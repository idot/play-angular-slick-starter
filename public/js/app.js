/**
* loads sub modules and wraps them up into the main module
* this should be used for top-level module definitions only
*/
define([
    'angular',
	'angular-cookies',
	'angular-animate',
	'd3',
	'nvd3',
	'angular-nvd3',
	'angularjs-toaster',
	'angular-ui',
	'angular-ui-router',
	'ng-table',
	'restangular',
    './controllers/index',
    './directives/index',
    './filters/index',
    './services/index'
], function (angular) {
    'use strict';

    return angular.module('app', [
	    'ngCookies', 
		'ngAnimate',
        'app.controllers',
        'app.directives',
        'app.filters',
        'app.services',
		'restangular',
		'nvd3ChartDirectives',
		'toaster',
		'ui',
		'ui.router',
		'ngTable'
    ]);
});
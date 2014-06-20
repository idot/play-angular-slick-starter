/*global require, requirejs */


'use strict';



var requirejsconfig = {
  paths: {
    'underscore': ['../lib/underscorejs/underscore'],
    'moment': ['../lib/momentjs/min/moment.min'],
	'domReady': ['../lib/requirejs-domready/domReady'],
    'angular': ['../lib/angularjs/angular'],
    'angular-cookies': ['../lib/angularjs/angular-cookies'],
    'angular-animate': ['../lib/angularjs/angular-animate'],
	'restangular': ['../lib/restangular/restangular'],
    'angular-ui': ['../lib/angular-ui/angular-ui'],
    'angular-ui-bootstrap': ['../lib/angular-ui-bootstrap/ui-bootstrap-tpls'],
    'angular-ui-router': ['../lib/angular-ui-router/angular-ui-router'],
    'angular-ui-utils': ['../lib/angular-ui-utils/ui-utils'],
	'd3': ['../lib/d3js/d3'],
	'nvd3': ['../lib/nvd3/nv.d3'],
	'angular-nvd3': ['../lib/angularjs-nvd3-directives/angularjs-nvd3-directives'],
	'ng-table': ['../lib/ng-table/ng-table'],
	'angularjs-toaster': ['../lib/angularjs-toaster/toaster']
  },
  shim: {
	'underscore': {
	  exports: 'underscore'
	},
	'moment' : {
	  exports: 'moment'	
	},
	'angular': {
      exports : 'angular'
    },
    'angular-cookies': {
      deps: ['angular']
    },
    'angular-animate': {
      deps: ['angular']
    },
    'restangular': {
        deps: ['underscore', 'angular']
    },
    'angular-ui': {
        deps: ['angular']
    },
    'angular-ui-bootstrap': {
        deps: ['angular-ui']
    },
    'angular-ui-router':{
        deps: ['angular-ui']
    },
    'angular-ui-utils': {
       deps: ['angular']
    },
	'angularjs-toaster': {
		deps: ['angular-animate']
	},
	'd3': {
		exports: 'd3'		
	},
	'nvd3': {
		deps: ['d3'],
		exports: 'nvd3'
	},
	'angular-nvd3': {
		deps: ['angular', 'nvd3']
	},
    'ng-table':{
        deps: ['angular']
    }
  }, 
  
  deps: [
      './bootstrap'
  ]
};


requirejs.config(requirejsconfig);


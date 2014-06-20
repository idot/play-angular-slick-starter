'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){

  console.log("ng", angular.version);	
 	
  beforeEach(module('app.controllers')); 


  it('should ....', inject(function($controller) {
    //spec body
    var myCtrl1 = $controller('MyCtrl1', { $scope: {} });
    expect(myCtrl1).toBeDefined();
  }));

  it('should ....', inject(function($controller) {
    //spec body
    var myCtrl2 = $controller('MyCtrl2', { $scope: {} });
    expect(myCtrl2).toBeDefined();
  }));
});

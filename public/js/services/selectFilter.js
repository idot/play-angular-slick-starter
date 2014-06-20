/**
*  helper for ng-table, must be a function returning a promise with special structure
*
*  add as: $scope.xyFilter = selectFilter.from{['good','bad']}
*  and in template: filter="{ 'quality': 'select' }" filter-data="xyFilter()">
*
**/
define(['./module'], function (services) {
    'use strict';
    services.factory('selectFilter', function($q){
       return {
               from: function(items){
                     return function(){
                        var arr = _.map(items, function(i){ return { id: i, title: i}; });
                        var deferred = $q.defer();
                        deferred.resolve(arr);
                        return deferred;
                     };
               }
       };
    })
});


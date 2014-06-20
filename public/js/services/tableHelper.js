/**
* table helper for ng-table
**/
define(['./module'], function (services) {
    'use strict';
    services.factory('tableHelper', function(ngTableParams, $filter){
        return{
            setupTable : function(collection, initialSorting){
			    var tableParams = new ngTableParams({
			        page: 1,                 // show first page
			        count: 15,               // count per page
			        sorting: initialSorting  // initial sorting
			    }, {
			        total: 0,           // length of data
			        getData: function($defer, params) {
			                        var data = collection;
			                        var orderedData = params.sorting() ? $filter('orderBy')(collection, params.orderBy()) : collection;
			                        orderedData = params.filter ? $filter('filter')(orderedData, params.filter()) : orderedData;
			                        params.total(orderedData.length);
			                        var slice = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count())
			                        $defer.resolve(slice);
			        }
			    });
				return tableParams;
             }
        }
   })
});
 
 
 
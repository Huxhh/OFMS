app.controller('home', ['$scope', function ($scope) {
	$scope.hotPage = 0;
	$scope.hotList = [];
	$scope.recommondPage = 0;
	$scope.recommondList = [];
	$scope.newPage = 0;
	$scope.newList = [];
	// 页码列表
    $scope.page_num = [];
    // 当前页数
    $scope.isActive = 0;
}]);
app.directive('homeDirective', ['request', '$state', function (request, $state) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			// 分类显示
			scope.$emit('classify_change', true);
			//directive作用域内全局变量
			var func;//用于随机切换电影的函数
			var page;//用于随机切换电影的页数
			var ran;//随机数
			//directive函数
			function get_page(url, type, callback) {
				if (!url) {
					console.error('url needed!!!');
					return false;
				}
				request.get(url + '/0/15', function (res) {
					if (res.code == 0) {
						if(type == 1) {
							scope.hotPage = res.body.totalPages;
						} else if (type == 2) {
							scope.recommondPage = res.body.totalPages;
						}
						callback(type);
					} else {
						request.pop_up(res.msg);
					}
				})
			}
			function get_recommend(page, size) {
				page = page || 0;
				size = size || 15;
				request.get('/recommend/buycount/' + page +  '/' + size,
					function (res) {
						if (res.code == 0) {
							scope.hotList = res.body.content;
							scope.hotPage = res.body.totalPages;
						} else {
							request.pop_up(res.msg);
					}
				})
			}
			function get_guess(page, size) {
				page = page || 0;
				size = size || 15;
				request.get('/recommend/buycount/' + page +  '/' + size,
					function (res) {
						if (res.code == 0) {
							scope.recommondList = res.body.content;
							scope.recommondPage = res.body.totalPages;
						} else {
							request.pop_up(res.msg);
					}
				})
			}
			scope.get_new = function(go_page, size, reset) {
				scope.isActive = go_page;
                page = go_page || 0;
				page = page || 0;
				size = size || 48;
				request.get('/recommend/buycount/' + page +  '/' + size,
					function (res) {
						if (res.code == 0) {
							scope.newList = res.body.content;
							scope.newPage = res.body.totalPages;
							final_page = Math.min(scope.newPage, max_page);
	                        if (reset) {
	                        	scope.page_num = [];
	                            for (var i = 0; i < final_page; ++i) {
	                                scope.page_num.push(i);
	                            }
	                        }
						} else {
							request.pop_up(res.msg);
					}
				})
			}
			// scope.choose_bg = function () {
			// 	angular.element('.list')
			// }
			scope.choose_one = function (id) {
				sessionStorage.setItem('filmID', id);
				// sessionStorage.setItem('getOneBy', 'id');
				$state.go('overview');
			}
			scope.random_change = function (type) {
				ran = Math.random();
				if (type == 1) {
					func = get_recommend;
					page = scope.hotPage;
				} else {
					func = get_guess;
					page = scope.recommondPage;
				}
				if (ran > 0.5) {
					func(Math.ceil(ran * page), 15);
				} else {
					func(Math.floor(ran * page), 15);
				}
			}
			//获取分类电影
			get_page('/recommend/buycount', 1, scope.random_change);
			get_page('/recommend/buycount', 2, scope.random_change);
			scope.get_new(0, 48, true);
			var max_page = 5, final_page;
			scope.prev = function () {
                if ((0 != scope.page_num[0]) && (scope.isActive == scope.page_num[0])) {
                    for (var i = 0; i < scope.page_num.length; ++i) {
                        scope.page_num[i]--;
                    }
                }
                if (scope.isActive != 0) {
                    scope.isActive--;
                }
                scope.get_new(scope.isActive, 48);
            }
            scope.next = function () {
                if ((scope.page != (scope.page_num[final_page - 1]) + 1) &&
                 	(scope.isActive == scope.page_num[final_page - 1])) {
                    for (var i = 0; i < scope.page_num.length; ++i) {
                        scope.page_num[i]++;
                    }
                }
                if (scope.isActive != (scope.page - 1)) {
                    scope.isActive++;
                }
                scope.get_new(scope.isActive, 48);
            }
		}
	}
}])
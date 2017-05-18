app.controller('home', ['$scope', function ($scope) {
	$scope.hotPage = 0;
	$scope.hotList = [];
	$scope.recommondList = [];
	// $scope.recommendRunList = [];
	$scope.newPage = 0;
	$scope.newList = [];
	// 页码列表
    $scope.page_num = [];
    // 当前页数
    $scope.isActive = 0;
    $scope.uid = 1;
    //轮播flag
    $scope.scrollerFlag = true;
    $scope.scrollerIndex = 0;
    $scope.autoRunFlag = true;
}]);
app.directive('homeDirective', ['request', '$state', function (request, $state) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			// 分类显示
			scope.$emit('classify_change', true);
			//directive作用域内全局变量
			var ran;//随机数
			var goal;//轮播
			var scroller, scrollerWidth;
			//directive函数
			function get_page(url, type, callback) {
				if (!url) {
					console.error('url needed!!!');
					return false;
				}
				request.get(url, function (res) {
					if (res.code == 0) {
						scope.hotPage = res.body.totalPages;
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
			function get_guess() {
				request.get('/recommend/favor/' + scope.uid,
					function (res) {
						if (res.code == 0) {
							scope.recommondList = res.body;
							scrollerWidth = document.getElementById('listfavor').clientWidth;
				            scroller = angular.element(document.getElementById('listfavor'));
				    		goal = Math.floor(scrollerWidth / 4);//用于轮播
						} else {
							request.pop_up(res.msg);
					}
				})
			}
			scope.get_new = function(go_page, size, reset) {
				scope.isActive = go_page;
                page = go_page || 0;
				page = page || 0;
				size = size || 32;
				request.get('/recentFilm//' + page + '/' + size,
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
				if (ran > 0.5) {
					get_recommend(Math.ceil(ran * scope.hotPage), 15);
				} else {
					get_recommend(Math.floor(ran * scope.hotPage), 15);
				}
			}
			//获取热门电影
			get_page('/recommend/buycount/0/15', 1, scope.random_change);
			// get_page('/recommend/favor/' + scope.uid, 2, scope.random_change);
			//猜你喜欢
			get_guess();
			//最新电影
			scope.get_new(0, 32, true);
			var max_page = 5, final_page;
			scope.prev = function () {
                if ((0 != scope.page_num[0]) && (scope.isActive == scope.page_num[0])) {
                    for (var i = 0; i < scope.page_num.length; ++i) {
                        scope.page_num[i]--;
                    }
                }
                if (scope.isActive != 0) {
                    scope.isActive--;
                } else {
                	return false;
                }
                scope.get_new(scope.isActive, 32);
            }
            scope.next = function () {
                if ((scope.newPage != (scope.page_num[final_page - 1]) + 1) &&
                 	(scope.isActive == scope.page_num[final_page - 1])) {
                    for (var i = 0; i < scope.page_num.length; ++i) {
                        scope.page_num[i]++;
                    }
                }
                if (scope.isActive != (scope.newPage - 1)) {
                    scope.isActive++;
                } else {
                	return false;
                }
                scope.get_new(scope.isActive, 32);
            }
            //轮播器
            var left = 0;
            var tmp;
            function move_left() {
				setTimeout(function () {
					
					if (left != -goal) {
						left--;
            			scroller.css({'left': left + 'px'})
            			move_left();
					} else {
						tmp = scope.recommondList[0];
						scope.recommondList = scope.recommondList.slice(1)
		        		scope.recommondList = scope.recommondList.concat(tmp);
	            		scope.scrollerFlag = true;
					}
				}, 1);
            }
            function move_right() {
				setTimeout(function () {
					if (left != 0) {
						left++;
            			scroller.css({'left': left + 'px'})
            			move_right();
					} else {
	            		scope.scrollerFlag = true;
					}
				}, 1);            	
            }
            scope.left = function () {
            	if (scope.scrollerFlag) {
            		scope.scrollerFlag = false;
	            	left = 0;
            		move_left();
            		scroller.css({'left': '0px'});
            	}
            }
            scope.right = function () {
            	if (scope.scrollerFlag) {
            		scope.scrollerFlag = false;
            		left = -goal;
					scroller.css({'left': left + 'px'})
					tmp = scope.recommondList[9];
					scope.recommondList = scope.recommondList.slice(0, 9)
	        		scope.recommondList = [tmp].concat(scope.recommondList);
            		move_right();
            	}
            }
            var start_run, one_run;
            scope.autoRun = function () {
            	if (scope.autoRunFlag) {
		            start_run = setInterval(function () {
		            	scope.right();
		            	scope.$apply();
		            }, 6000);
		        } else {
		        	clearInterval(start_run);
		        }
            }
            scope.autoRun();
            window.onresize = function () {
	    		goal = Math.floor(scrollerWidth / 4);//用于轮播
        		console.log(goal)
            }
		}
	}
}])
app.controller('search', ['$scope', function ($scope) {
	$scope.filmList = [];
	// 页码总计
    $scope.searchPage = 0;
    // 页码列表
    $scope.page_num = [];
    // 当前页数
    $scope.isActive = 0;
}]);
app.directive('searchDirective', ['request', '$state', function (request, $state) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			var max_page = 10, final_page;
			var url, classify_show;
			scope.search_film = function(go_page, reset) {
				scope.isActive = go_page;
                page = go_page || 0;
				switch(sessionStorage.getItem('getOneBy')) {
					case 'kind':
						url = '/search/kind?kind=' + sessionStorage.getItem('filmKind')
							+ '&page=' + page;
						classify_show = true;
						break;
					case 'name':
						url = '/search/name?name=' + sessionStorage.getItem('filmName')
							+ '&page=' + page;
						classify_show = false;
						break;
					default:
						break;
				}
				scope.$emit('classify_change', classify_show);
				request.get(url, function (res) {
					if (res.status == 1) {
						scope.filmList = res.movie.content;
						scope.searchPage = res.movie.totalPages;
						final_page = Math.min(scope.searchPage, max_page);
                        if (reset) {
                        	scope.page_num = [];
                            for (var i = 0; i < final_page; ++i) {
                                scope.page_num.push(i);
                            }
                        }
					} else {
						request.pop_up(res.message);
					}
				})
			}
            scope.search_film(0, true);
			scope.choose_one = function (id) {
				sessionStorage.setItem('filmID', id);
				// sessionStorage.setItem('getOneBy', 'id');
				$state.go('overview');
			}
            scope.prev = function () {
                if ((0 != scope.page_num[0]) && (scope.isActive == scope.page_num[0])) {
                    for (var i = 0; i < scope.page_num.length; ++i) {
                        scope.page_num[i]--;
                    }
                }
                if (scope.isActive != 0) {
                    scope.isActive--;
                }
                scope.search_film(scope.isActive);
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
                scope.search_film(scope.isActive);
            }
			scope.$on('search_click', function (event, data) {
				scope.search_film(0, true);
			});
		}
	}
}])
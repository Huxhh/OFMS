app.controller('overview', ['$scope', function ($scope) {
	$scope.filmOverview = null;
}]);
app.directive('overviewDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			//分类不显示
			scope.$emit('classify_change', false);
			var tmp_star;
			request.get('/film/' + sessionStorage.getItem('filmID'), function (res) {
				if (res.code == 0) {
					scope.filmOverview = res.body;
					tmp_star = res.body.star;
					scope.filmOverview['filmtext'] = '暂无';
					request.resize();
					request.post('/film/filmtext', {
						film: scope.filmOverview.name
					}, function (res) {
						if (res.code == 0) {
							scope.filmOverview['filmtext'] = res.body;
						} else {
							request.pop_up(request.mes);
						}
					})
					if (tmp_star) {
						scope.filmOverview.star = tmp_star.slice(1, tmp_star.length - 1).split(',');
					} else {
						scope.filmOverview.star = ['0%', '0%', '0%', '0%', '0%'];
					}
				} else {
					request.pop_up(res.message);
				}
			})
			scope.buy = function () {
				request.get('/usr/ifLogin', function (response) {
					if (response.code == 0) {
						request.comfirm(function () {
							request.post('/user/buyfilm', {
								uid: response.body.id.toString(),
								fid: scope.filmOverview.id.toString()
							}, function (res) {
								request.pop_up(res.msg);
							})
						})
					} else {
						scope.removeUser();
						request.pop_up('请登录');
					}
				})
			}
			// scope.save = function () {
			// 	// request.comfirm('/user/')
			// }
		}
	}
}])
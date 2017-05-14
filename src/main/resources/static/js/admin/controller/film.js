app.controller('film', ['$scope', function ($scope) {
	$scope.filmList = [];
	// 页码总计
    $scope.filmPage = 0;
    // 页码列表
    $scope.page_num = [];
    // 当前页数
    $scope.isActive = 0;
    //可编辑
    $scope.firstEditFlag = true;
    $scope.oldEdit = 0;
    //坑有点大，不推荐contenteditable来进行双向数据绑定
    //这里这样做不确定是否有内存泄漏的危险存在
    //深拷贝和浅拷贝需要
    //oldFilm浅拷贝，用于更新filmList
    //oldFilmTmp深拷贝，用于更新oldFilm
    //当只有深拷贝时，不清楚原因，页面没有重新渲染。angular机制有关
    //当只有浅拷贝，filmList和oldFilm指向同一个地址，然后在函数最后内容被修改
    //已修改为input，去掉了oldfilmtmp
    $scope.oldFilm = {};
    $scope.editAble = [];
    //添加
    $scope.addFilmFlag = false;
    $scope.addFilmKey = ['name', 'otherName', 'country', 'language',
    'director', 'writer', 'kind', 'actor', 'length', 'image', 'url'];
    $scope.addFilmKeyZh = ['名称', '别名', '地区', '语言', '导演', '编剧',
    '类型', '演员', '时长', '图片链接', '链接'];
    $scope.addFilm = {
        id: 0,
        name: null,
        otherName: null,
        country: null,
        language: null,
        director: null,
        writer: null,
        kind: null,
        date: null,
        actor: null,
        length: null,
        buyCount: null,
        image: null,
        url: null,
        voteCount: 0,
        buyCount: 0,
        score: 0
    }
    //修改发送数据
    $scope.sendFilm = {};
    for (i in $scope.addFilm) {
        $scope.sendFilm[i] = $scope.addFilm[i];
    }
    //search
    $scope.searchKey = null;
    $scope.ifSearch = false;
}]);
app.directive('filmDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			var max_page = 10, final_page;
            var type, geturl;
            //search
            sessionStorage.setItem('searchBy', 'name');
            scope.searchBy = function (value) {
                sessionStorage.setItem('searchBy', value);
            }
            //get film
			scope.admin_film = function(go_page, reset, search) {
                page = go_page || 0;
                sessionStorage.setItem('filmPage', page);
                if (search) {
                    scope.ifSearch = true;
                    type = sessionStorage.getItem('searchBy');
                    geturl = '/admin/search/' + type + '?' + type + '=' + scope.searchKey + '&page=' + page;
                } else {
                    scope.ifSearch = false;
                    geturl = '/admin/?page=' + page;
                }
				request.get(geturl, function (res) {
					if (res.code == 0) {
						scope.filmList = res.body.content;
						scope.filmPage = res.body.totalPages;
						scope.editAble.length = res.body.size;
						scope.editAble.fill(true);
						scope.firstEditFlag = true;
                        scope.isActive = page;
						final_page = Math.min(scope.filmPage, max_page);
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
			scope.prev = function (search) {
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
                scope.admin_film(scope.isActive, false, search);
            }
            scope.next = function (search) {
                if ((scope.filmPage != (scope.page_num[final_page - 1]) + 1) &&
                 	(scope.isActive == scope.page_num[final_page - 1])) {
                    for (var i = 0; i < scope.page_num.length; ++i) {
                        scope.page_num[i]++;
                    }
                }
                if (scope.isActive != (scope.filmPage - 1)) {
                    scope.isActive++;
                } else {
                    return false;
                }
                scope.admin_film(scope.isActive, false, search);
            }
            scope.admin_film(0, true);
            scope.change_film = function (index, cancle) {
            	if (!scope.firstEditFlag && scope.oldEdit != index) {
                    for (i in scope.oldFilm) {
                        scope.filmList[scope.oldEdit][i] = scope.oldFilm[i];
                    }
            	} else {
	            	scope.firstEditFlag = false;
            	}
                if (!cancle) {
                    scope.editAble[scope.oldEdit] = true;
                	scope.editAble[index] = false;
                	scope.oldEdit = index;
                } else {
                    scope.editAble[scope.oldEdit] = true;
                }
                for (i in scope.filmList[index]) {
                    if (i != "$$hasnkey")
                        scope.oldFilm[i] = scope.filmList[index][i];
                }
            }
            scope.submit = function (index) {
                for (i in scope.sendFilm) {
                    scope.sendFilm[i] = scope.filmList[index][i];
                }
                request.put('/admin/', [scope.sendFilm], function (res) {
                    if (res.code == 0) {
                        scope.editAble[index] = true;
                    } else {
                        request.pop_up(res.msg);
                    }
                })
            }
            scope.delete_film = function (id, index) {
                for (i in scope.sendFilm) {
                    scope.sendFilm[i] = scope.filmList[index][i];
                }
                request.post('/admin/delete', [scope.sendFilm], function (res) {
                    if (res.code == 0) {
                        scope.admin_film(0, true);
                    }
                })
            }
            scope.add_film = function () {
                scope.addFilmFlag = true;
                scope.$emit('addFilmOverflow', true);
            }
            scope.add_submit = function () {
                for (i in scope.addFilm) {
                    if (scope.addFilm[i] == null) {
                        request.pop_up('必须填写完整');
                        return false;
                    }
                }
                request.post('/admin/', [scope.addFilm], function (res) {
                    if (res.code == 0) {
                        console.log(res);
                        for (i in scope.addFilm) {
                            scope.addFilm = null;
                        }
                        scope.addFilm['id'] = 0;
                        scope.addFilm['score'] = 0;
                        scope.addFilm['buyCount'] = 0;
                        scope.addFilm['voteCount'] = 0;
                    } else {
                        request.pop_up(res.msg);
                    }
                })
            }
            scope.add_cancle = function () {
                scope.$emit('addFilmOverflow', false);
                scope.addFilmFlag = false;
                for (i in scope.addFilm) {
                    scope.addFilm = null;
                }
                scope.addFilm['id'] = 0;
                scope.addFilm['score'] = 0;
                scope.addFilm['buyCount'] = 0;
                scope.addFilm['voteCount'] = 0;
            }
		}
	}
}]);
/**
 * Created by xuhang on 2019/11/10.
 */
;
define(function (require, exports, module) {
    "require:nomunge,exports:nomunge,module:nomunge";

    /**
     * @class List
     * @constructor
     */
    function List() {
        this.init();
        console.log('List init calling');
    }

    var paginator = require('bootstrap-paginator');


    /**
     * method to init page
     */
    List.prototype.init = function () {
        var self = this;
        self.getData(1, true);
    }


    /**
     * method to get data
     */
    List.prototype.getData = function (page, showPaginator) {

        var self = this;

        var year = $('#year  option:selected').text();

        var place = $('#place  option:selected').text();

        var type = $('#type  option:selected').text();

        var sort = $('#sort  option:selected').val();

        var key = $('#key').val();


        var filmList = $('#film-list');

        $.getJSON("/category/list", {
            pageNo: page,
            year: year,
            place: place,
            type: type,
            sort: sort,
            key: key
        }, function (data) {
            if (data != null) {
                filmList.html("");
                $.each(data.resultList, function (index, item) {
                    filmList.append('<li><div class="space"><div class="pull-left"><img src="' + item.image + '" width="100px" height="140px"></div>\
                    <div class="pull-left margin-left-10 film-desc"><div class ="margin-bottom-10"><a  style="color: #9B8282;" href="/subject/' + item.id + '">' + item.title + "(" + item.year + ")" + '</a></div>\
                    <table class="table" style="font-size:12px;"><tbody><tr>\
                    <td width="50px">Score</td><td><span class="badge" style="color: orange; font-weight: bold;">' + item.rating.toFixed(2) + '</span></td>\
                    </tr><tr><td>Genre</td><td>' + (item.genres == null ? "" : item.genres) + '</td></tr><tr><td>Starring</td><td>' + (item.casts == null ? "" : item.casts) + '</td></tr>\
                    </tbody></table></div><div class="clearfix"></div></div></li>');
                });
                if (showPaginator) {
                    var pageCount = data.totalPages;
                    var currentPage = data.pageNo;
                    var options = {
                        bootstrapMajorVersion: 3,
                        currentPage: currentPage,
                        totalPages: pageCount,
                        itemTexts: function (type, page, current) {
                            switch (type) {
                                case "first":
                                    return "First";
                                case "prev":
                                    return "Previous";
                                case "next":
                                    return "Next";
                                case "last":
                                    return "Last";
                                case "page":
                                    return page;
                            }
                        },
                        onPageClicked: function (event, originalEvent, type, page) {
                            self.getData(page, false);
                        }
                    };
                    $('#paginator').bootstrapPaginator(options);
                }
            }
        });


        $('#year').change(function () {
            self.getData(1, true);
        });
        $('#place').change(function () {
            self.getData(1, true);
        });
        $('#type').change(function () {
            self.getData(1, true);
        });
        $('#sort').change(function () {
            self.getData(1, true);
        });
    };


    module.exports = new List();
})
;
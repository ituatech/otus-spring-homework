$(document).ready(function() {
    $(window).on('load', function () {
        callPage("list.html", list)
    })
})

function callPage(pageRefInput, foo, param) {
    $.ajax({
        url: pageRefInput,
        type: "GET",
        dateType: 'text',
        success: function (response) {
            $('.content').html(response)
            if (foo != undefined) {
                foo(param)
            }
        }
    })
}

function list() {
    $.ajax({
        url: '/books',
        dataType: "json",
        success: function (data, textStatus) {
            $('.add-book').on('click', function (e) {
                e.preventDefault()
                callPage("add.html", addBook)
            })
            $.each(data, function (i, val) {
                $(".book-list").append("<tr>" +
                    "<td>" + val.id + "</td>" +
                    "<td>" + val.title + "</td>" +
                    "<td>" + val.description + "</td>" +
                    "<td><a class='book-show' href='/books/" + val.id + "/show'>View</a></td>" +
                    "<td><a class='book-update' href='/books/" + val.id + "/update'>Update</a></td>" +
                    "<td><a class='book-delete' href='/books/" + val.id + "/delete'>Delete</a></td>" +
                    "</tr>")
            });
            showBook()
            deleteBook()
            updateBook()
        }
    })
}

function showBook(id) {
    $('.book-show').on('click', function (e) {
        e.preventDefault()
        if (id == undefined) {
            var pageRef = $(this).attr('href')
            id = pageRef.substring(7, pageRef.lastIndexOf('/'))
        }
        callPage("view.html", showBook, id)
        getBook(id)
    })
}

function addBook() {
    addNewAuthor()
    addNewGenre()
    $('.add-new-book').on('click', function (e) {
        e.preventDefault()
        var authors = [];
        var genres = [];
        $('.new-book-authors').each(function (i, obj) {
            authors.push(obj.value)
        })
        $('.new-book-genres').each(function (i, obj) {
            genres.push(obj.value)
        })
        var result = {
            title: $('.new-book-title').val(),
            description: $('.new-book-description').val(),
            authors: authors,
            genres: genres
        }
        $.ajax({
            url: '/books/add',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(result),
            success: function (data) {
                callPage("view.html", getBook, data.id)
            }
        })
    })
}

function updateBook(id) {
    $('.book-update').on('click', function (e) {
        e.preventDefault()
        if (id == undefined) {
            var pageRef = $(this).attr('href')
            id = pageRef.substring(7, pageRef.lastIndexOf('/'))
        }
        callPage("update.html", updateBook, id)
        getBookForUpdate(id)
    })
    $('.update-book').on('click', function (e) {
        e.preventDefault()
        var authors = [];
        var genres = [];
        $('.update-book-authors-name').each(function (i, obj) {
            authors.push(obj.value)
        })
        $('.update-book-genres-name').each(function (i, obj) {
            genres.push(obj.value)
        })
        var result = {
            id: id,
            title: $('.update-book-title').val(),
            description: $('.update-book-description').val(),
            authors: authors,
            genres: genres
        }
        $.ajax({
            url: '/books/update',
            type: 'put',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(result),
            success: function (data) {
                callPage("view.html", getBook, data.id)
            }
        })
    })
}

function getBookForUpdate(id) {
    $.ajax({
        url: '/books/'  + id + '/show',
        dataType: "json",
        success: function (data, textStatus) {
            $(".update-book-title").val(data.title)
            $(".update-book-description").val(data.description)
            $(".update-book-authors").append("<tr>")
            for (var i in data.authors) {
                $(".update-book-authors").append("<div id='field'></div>")
                .append("<input type='text' class='text-input update-book-authors-name' id='field" + (parseInt(i, 10) + 555) + "' name='authors' value='" + data.authors[i].authorName +"'/>")
                .append("<button class='btn btn-danger remove-me' id='remove" + (parseInt(i, 10) + 555) + "'>-</button>")
            }
            $(".update-book-authors").append("</tr>")
            .append("<br>\n" +
                "<input type=\"text\" class=\"text-input update-book-authors-name\" id=\"field666\" name=\"authors\"/>" +
                "<button id=\"b1\" class=\"btn add-more-author\" type=\"button\">+</button>")

            $(".update-book-genres").append("<tr>")
            for (var i in data.genres) {
                $(".update-book-genres").append("<div id='field'></div>")
                    .append("<input type='text' class='text-input update-book-genres-name' id='gfield" + (parseInt(i, 10) + 555) + "' name='genres' value='" + data.genres[i].genreName +"'/>")
                    .append("<button class='btn btn-danger gremove-me' id='gremove" + (parseInt(i, 10) + 555) + "'>-</button>")
            }
            $(".update-book-genres").append("</tr>")
                .append("<br>\n" +
                    "<input type=\"text\" class=\"text-input update-book-genres-name\" id=\"gfield666\" name=\"genres\"/>" +
                    "<button id=\"b1\" class=\"btn add-more-genre\" type=\"button\">+</button>")
            updateAuthor()
            updateGenre()
            removeAuthor()
            removeGEnre()
        }
    })
}

function deleteBook(id) {
    $('.book-delete').on('click', function (e) {
        e.preventDefault()
        if (id == undefined) {
            var pageRef = $(this).attr('href')
            id = pageRef.substring(7, pageRef.lastIndexOf('/'))
        }
        $.ajax({
            url: '/books/'  + id + '/delete',
            type: 'delete'
        })
        setTimeout(function () {
            callPage("list.html", list)
        }, 250)
    })
}

function getBook(id) {
    $.ajax({
        url: '/books/'  + id + '/show',
        dataType: "json",
        success: function (data, textStatus) {
            $(".book-id").append("<p>" + data.id + "</p>")
            $(".book-title").append("<p>" + data.title + "</p>")
            $(".book-description").append("<p>" + data.description + "</p>")
            $.each(data.authors, function (i, val) {
                $(".book-authors").append("<li>" + val.authorName + "</li>")
            })
            $.each(data.genres, function (i, val) {
                $(".book-genres").append("<li>" + val.genreName + "</li>")
            })
            $.each(data.comments, function (i, val) {
                $(".book-comment").append("<p></p><dd>" + val.userName + "</dd>" +
                    "<dd>" + val.createdAt + "</dd>" +
                    "<dd>" + val.commentText + "</dd></p>")
            })
            addComment(id)
        }
    })
}

function addComment(id) {
    $('.add-comment').on('click', function (t) {
        t.preventDefault()
        callPage("/add-comment.html", sendComment, id)
    })
}

function sendComment(id) {
    $('.send-comment').on('click', function (r) {
        r.preventDefault()
        var result = {
            userName: $(".user-name").val(),
            commentText: $(".comment-text").val()
        }
        var url = '/books/' + id + '/comments'
        $.ajax({
            url: url,
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(result),
            success: function (data) {
                callPage("view.html", getBook, id)
            }
        })
    })
}
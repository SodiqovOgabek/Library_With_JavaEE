
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
            aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <div class="form-group mb-0 mr-3">
            <select class="form-control" name="genre" id="genre" onchange="window.location.href='/genre/'+this.value;">
                <c:forEach items="${genres}" var="genre">
                    <option value="" disabled selected hidden>Choose genre</option>
                    <option value="${genre.getKey()}">${genre.getKey()}</option>
                </c:forEach>
            </select>
        </div>
        <form class="form-inline my-2 my-lg-0" action="/" method="get">
            <input class="form-control mr-sm-2" name="search" type="search" value="${search}"
                   placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
        <button class="btn btn-outline-danger ml-2 my-2 my-sm-0" type="button" onclick="location='/logout'">Logout
        </button>
    </div>
</nav>

<button type="button" class="btn btn-success mt-4 mb-4 text-white" data-toggle="modal" data-target="#exampleModal">
    ➕ Add
</button>
<c:if test="${user.status.priority>50}">
    <button type="button" class="btn btn-success mt-4 mb-4 text-white" onclick="window.location.href='/admin'">
        Pending Books
    </button>
</c:if>

<c:if test="${pageInfo.totalPages>0}">
    <nav aria-label="Pagination">
        <ul class="pagination">
            <c:if test="${pageInfo.hasPrevious}">
                <li class="page-item">
                    <a class="page-link" href="?search=${search}&page=${pageInfo.number-1}" tabindex="-1">Previous</a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="?search=${search}&page=${pageInfo.number-1}"
                       tabindex="-1">${pageInfo.number-1}</a>
                </li>
            </c:if>

            <li class="page-item active"><a class="page-link" href="?search=${search}&page=1">${pageInfo.number}</a>
            </li>

            <c:if test="${pageInfo.hasNext}">
                <li class="page-item">
                    <a class="page-link" href="?search=${search}&page=${pageInfo.number+1}"
                       tabindex="-1">${pageInfo.number+1}</a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="?search=${search}&page=${pageInfo.number+1}" tabindex="-1">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</c:if>
<div class="modal fade bd-example-modal-lg" id="exampleModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Save book</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="/uploads/add" enctype="multipart/form-data">
                    <div class="row">

                        <div class="col-md-6">
                            <div class="form-group mb-3">
                                <label>Name</label>
                                <input type="text" name="name" class="form-control" placeholder="Book name"/>
                            </div>

                            <div class="form-group mb-3">
                                <label>Author</label>
                                <input type="text" name="author" class="form-control" placeholder="Book author"/>
                            </div>

                            <div class="form-group mb-3">
                                <label>Genre</label>
                                <select class="form-control" name="genre" id="book_genre">
                                    <c:forEach items="${genres}" var="genre">
                                        <option value="${genre.name()}">${genre.getKey()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group mb-3">
                                <label>Language</label>
                                <select class="form-control" name="language" id="language">
                                    <c:forEach items="${languages}" var="language">
                                        <option value="${language.name()}">${language.getValue()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group mb-3">
                                <label>Page Count</label>
                                <input type="number" name="pageCount" class="form-control"
                                       placeholder="Book pageCount"/>
                            </div>
                            <div class="form-group mb-3">
                                <label>Description</label>
                                <input type="text" name="description" class="form-control"
                                       placeholder="description..."/>
                            </div>
                            <div class="form-group mb-3">
                                <label>Book</label>
                                <input type="file" name="file" class="form-control" placeholder="Book"/>
                            </div>

                            <button type="submit" class="btn btn-primary">save</button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<div class="row m-2">
    <c:forEach items="${books}" var="book">
        <div class="col-2">
            <div class="card p-2" style="   width: 100%;">
                <img class="card-img-top" id="example"
                     src="/download?img=${book.cover.path}" width="140"
                     height="250"
                     alt="Card image cap">
                <div class="card-body p-1">
                    <h5 class="card-title"> ${book.name}</h5>
                    <i style="display:block;"><b>author</b> : ${book.author} </i>
                    <i style="display:block;"><b>genre</b> : ${book.genre.getKey()}</i>
                    <i style="display:block;"><b>language</b> : ${book.language}</i>
                    <i style="display:block;"><b>pageCount</b> : ${book.pageCount}</i>
                    <i style="display:block;"><b>downloadCount</b> : ${book.downloadCount}</i>
                    <button type="button" class="btn btn-warning"
                            onclick="location='/uploads/get?path=${book.cover.path}'">Get Cover
                    </button>
                    <button type="button" class="btn btn-success "
                            onclick="location='/uploads/get?path=${book.file.path}'">Get Book
                    </button>
                </div>
            </div>
        </div>
    </c:forEach>
</div>


<section>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</section>

</body>
</html>

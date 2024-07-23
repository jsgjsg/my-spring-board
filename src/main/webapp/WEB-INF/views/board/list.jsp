<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Board List</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <form action="/logout" method="post" class="inline">
        <button class="bg-red-500 text-white py-3 px-6 rounded-lg hover:bg-red-600">
            로그아웃
        </button>
    </form>
    <br/><br/>

    <!-- Search Form -->
    <form action="/board/list" method="get" class="flex mb-4">
        <input type="text" name="search" class="w-5/6 px-3 py-2 border border-gray-300 rounded-l-lg" placeholder="검색어를 입력하세요" value="${param.search}"/>
        <button type="submit" class="bg-blue-500 text-white px-2 py-2 rounded-r-lg hover:bg-blue-600">검색</button>
    </form>

    <table class="min-w-full bg-white border border-gray-300 mb-20">
        <thead>
        <tr class="bg-gray-200">
            <th class="py-3 px-4 border-b border-gray-300">ID</th>
            <th class="py-3 px-4 border-b border-gray-300">Title</th>
            <th class="py-3 px-4 border-b border-gray-300">Writer</th>
            <th class="py-3 px-4 border-b border-gray-300">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="board" items="${boards}">
            <tr class="hover:bg-gray-100">
                <td class="py-3 px-4 border-b border-gray-300">${board.id}</td>
                <td class="py-3 px-4 border-b border-gray-300"><a href="/board/${board.id}" class="text-blue-500 hover:underline">${board.title}</a></td>
                <td class="py-3 px-4 border-b border-gray-300">${board.writer}</td>
                <td class="py-3 px-4 border-b border-gray-300 space-x-2">
                    <c:if test="${board.writer == username}">
                        <a href="/board/${board.id}/edit" class="text-blue-500 hover:underline">Edit</a>
                        <form action="/board/${board.id}/delete" method="post" class="inline">
                            <button type="submit" class="text-red-500 hover:underline">Delete</button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Pagination -->
    <div class="flex justify-center space-x-2">
        <c:if test="${page > 1}">
            <a href="?page=${page - 1}&search=${param.search}" class="bg-gray-300 text-gray-700 px-4 py-2 rounded hover:bg-gray-400">Previous</a>
        </c:if>
        <c:set var="startPage" value="${page - (page - 1) % 10}" />
        <c:set var="endPage" value="${startPage + 9}" />
        <c:choose>
            <c:when test="${endPage > totalPages}">
                <c:set var="endPage" value="${totalPages}" />
            </c:when>
        </c:choose>
        <c:forEach var="i"
                   begin="${startPage}"
                   end="${endPage}">
            <a href="?page=${i}&search=${param.search}" class="px-4 py-2 rounded
                <c:choose>
                    <c:when test="${i == page}">
                        bg-blue-500 text-white
                    </c:when>
                    <c:otherwise>
                        bg-gray-300 text-gray-700 hover:bg-gray-400
                    </c:otherwise>
                </c:choose>">
                    ${i}
            </a>
        </c:forEach>
        <c:if test="${page < totalPages}">
            <a href="?page=${page + 1}&search=${param.search}" class="bg-gray-300 text-gray-700 px-4 py-2 rounded hover:bg-gray-400">Next</a>
        </c:if>
    </div>
</div>
<a href="/board/write" class="fixed bottom-5 right-5">
    <button class="bg-green-500 text-white py-3 px-6 rounded-lg hover:bg-green-600">
        새 글 등록
    </button>
</a>
</body>
</html>

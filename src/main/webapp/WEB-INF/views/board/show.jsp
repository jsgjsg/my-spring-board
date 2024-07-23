<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Board</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <!-- 게시글 테이블 -->
    <div class="container mx-auto p-4">
        <div class="bg-white border border-gray-300 rounded-lg shadow-md p-6">
            <h1 class="text-2xl font-bold mb-4">${boardAndComments.board.title}</h1>
            <div class="mb-4">
                <p class="text-gray-700"><strong>ID:</strong> ${boardAndComments.board.id}</p>
            </div>
            <div class="mb-4">
                <p class="text-gray-700"><strong>Content:</strong> ${boardAndComments.board.content}</p>
            </div>
            <div class="mb-4">
                <p class="text-gray-700"><strong>Writer:</strong> ${boardAndComments.board.writer}</p>
            </div>
            <c:if test="${boardAndComments.board.writer == username}">
                <div class="flex space-x-4">
                    <a href="/board/${boardAndComments.board.id}/edit" class="bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600">Edit</a>
                    <form action="/board/${boardAndComments.board.id}/delete" method="post" class="inline">
                        <button type="submit" class="bg-red-500 text-white py-2 px-4 rounded-lg hover:bg-red-600">Delete</button>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
    <br/>
    <!-- 댓글 목록 UI -->
    <div class="mt-6 bg-white p-4 rounded-lg shadow-md">
        <h2 class="text-xl font-bold mb-2">댓글 목록</h2>
        <ul>
            <c:forEach var="comment" items="${boardAndComments.comments}">
                <li class="flex justify-between items-center border-b border-gray-300 pb-4">
                    <div>
                        <p class="text-gray-800"><strong>${comment.username}</strong> - ${comment.content}</p>
                        <p class="text-sm text-gray-600">${comment.created_at}</p>
                    </div>
                    <c:if test="${comment.username == username}">
                        <div>
                            <form action="/comment/${comment.id}/delete" method="post" class="inline">
                                <input type="hidden" name="boardId" value="${boardAndComments.board.id}">
                                <button type="submit" class="text-red-500 hover:underline">Delete</button>
                            </form>
                        </div>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </div>
    <br/>
    <!-- 댓글 작성 UI -->
    <div class="mb-4 bg-white p-4 rounded-lg shadow-md">
        <h2 class="text-xl font-bold mb-2">댓글 작성</h2>
        <form action="/comment/create" method="post" class="space-y-4">
            <input type="hidden" name="boardId" id="boardId" value="${boardAndComments.board.id}">
            <div>
                <label for="username" class="block text-sm font-medium text-gray-700">작성자</label>
                <input type="hidden" name="username" id="username" value="${username}" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-200 focus:ring-opacity-50">
                ${username}
            </div>
            <div>
                <label for="content" class="block text-sm font-medium text-gray-700">내용</label>
                <textarea name="content" id="content" rows="4" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-200 focus:ring-opacity-50" required></textarea>
            </div>
            <div class="mt-4">
                <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded">댓글 작성</button>
            </div>
        </form>
    </div>

    <br/>
    <a href="/board/list">
        <button class="bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600">
            목록 보기
        </button>
    </a>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Board Edit</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <div class="bg-white border border-gray-300 rounded-lg shadow-md p-6">
        <form action="/board/${board.id}/update" method="post">
            <div class="mb-4">
                <label class="block text-gray-700 font-bold mb-2" for="title">Title</label>
                <input type="text" id="title" name="title" value="${board.title}" class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="제목" required />
            </div>
            <div class="mb-4">
                <label class="block text-gray-700 font-bold mb-2" for="content">Content</label>
                <textarea id="content" name="content" class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="내용">${board.content}</textarea>
            </div>
            <div class="mb-4">
                <label class="block text-gray-700 font-bold mb-2">Writer</label>
                <input type="hidden" name="writer" value="${board.writer}" />
                <p class="text-gray-700">${board.writer}</p>
            </div>
            <div class="flex space-x-4">
                <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Update</button>
                <a href="/board/${board.id}" class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>

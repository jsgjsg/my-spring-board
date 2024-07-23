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
    <div class="bg-white border border-gray-300 rounded-lg shadow-md p-6">
        <form action="/board/create" method="post">
            <div class="mb-4">
                <label for="title" class="block text-gray-700 font-bold mb-2">Title</label>
                <input type="text" id="title" name="title" class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="제목" required />
            </div>
            <div class="mb-4">
                <label for="content" class="block text-gray-700 font-bold mb-2">Content</label>
                <textarea id="content" name="content" class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="내용" required></textarea>
            </div>
            <input type="hidden" name="writer" value="${username}" />
            <div class="mb-4">
                <p class="text-gray-700 font-bold">Writer</p>
                <p class="text-gray-700">${username}</p>
            </div>
            <div class="flex space-x-4">
                <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Create</button>
                <a href="/board/list" class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>

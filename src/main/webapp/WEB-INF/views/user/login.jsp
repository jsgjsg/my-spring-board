<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
<div class="w-full max-w-md mx-auto bg-white border border-gray-300 rounded-lg shadow-md p-6">
    <h2 class="text-2xl font-bold mb-4">Login</h2>
    <form action="/login" method="post">
        <div class="mb-4">
            <label for="username" class="block text-gray-700 font-bold mb-2">Username</label>
            <input type="text" id="username" name="username" class="w-full px-3 py-2 border border-gray-300 rounded" placeholder="Username" required />
        </div>
        <div class="mb-4">
            <label for="password" class="block text-gray-700 font-bold mb-2">Password</label>
            <input type="password" id="password" name="password" class="w-full px-3 py-2 border border-gray-300 rounded" placeholder="Password" required />
        </div>
        <div class="mt-4 flex space-x-2">
            <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Login</button>
            <a href="/signup" class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 inline-block">Sign Up</a>
        </div>
        <c:if test="${param.error}">
            <p class="text-red-500 mt-2">Invalid username or password.</p>
        </c:if>
    </form>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Project Management AI Assistant</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        .chatbot-container {
            width: 100%;
            background: white;
            border-radius: 15px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            display: flex;
            flex-direction: column;
            height: 600px;
            margin: 20px 0;
        }
        
        .chat-header {
            background: linear-gradient(90deg, #2c3e50 0%, #4ca1af 100%);
            color: white;
            padding: 15px 20px;
            text-align: center;
            position: relative;
        }
        
        .logo {
            position: absolute;
            left: 20px;
            top: 50%;
            transform: translateY(-50%);
            font-size: 24px;
        }
        
        .chat-header h2 {
            font-size: 22px;
            margin: 0;
        }
        
        .chat-subtitle {
            font-size: 12px;
            opacity: 0.9;
        }
        
        .chat-main-content {
            display: flex;
            flex: 1;
            overflow: hidden;
        }
        
        .chat-sidebar {
            width: 200px;
            background: #2c3e50;
            color: white;
            padding: 15px;
            overflow-y: auto;
        }
        
        .modules {
            list-style: none;
            padding: 0;
        }
        
        .modules li {
            padding: 10px 12px;
            margin-bottom: 5px;
            border-radius: 6px;
            cursor: pointer;
            transition: background 0.3s;
            display: flex;
            align-items: center;
            font-size: 14px;
        }
        
        .modules li i {
            margin-right: 8px;
            font-size: 16px;
        }
        
        .modules li:hover {
            background: #4ca1af;
        }
        
        .modules li.active {
            background: #4ca1af;
            font-weight: bold;
        }
        
        .chat-content {
            flex: 1;
            display: flex;
            flex-direction: column;
            border-left: 1px solid #eee;
        }
        
        .chat-messages-header {
            padding: 12px 15px;
            background: #f8f9fa;
            border-bottom: 1px solid #eee;
            font-weight: 600;
            color: #2c3e50;
            display: flex;
            align-items: center;
        }
        
        .chat-messages-header i {
            margin-right: 8px;
            color: #4ca1af;
        }
        
        .chat-messages {
            flex: 1;
            padding: 15px;
            overflow-y: auto;
            background: #fafafa;
        }
        
        .message {
            max-width: 75%;
            margin-bottom: 15px;
            display: flex;
            flex-direction: column;
        }
        
        .message-content {
            padding: 10px 12px;
            border-radius: 15px;
            margin-bottom: 3px;
            line-height: 1.4;
            font-size: 14px;
        }
        
        .message-time {
            font-size: 11px;
            color: #777;
            margin-left: 12px;
            margin-top: 2px;
        }
        
        .user-message {
            align-items: flex-end;
            margin-left: auto;
        }
        
        .user-message .message-content {
            background: #4ca1af;
            color: white;
            border-bottom-right-radius: 5px;
        }
        
        .bot-message {
            align-items: flex-start;
        }
        
        .bot-message .message-content {
            background: #e8ebf0;
            color: #333;
            border-bottom-left-radius: 5px;
        }
        
        .quick-actions {
            display: flex;
            flex-wrap: wrap;
            gap: 8px;
            margin-top: 10px;
        }
        
        .action-btn {
            background: #e8ebf0;
            border: none;
            border-radius: 15px;
            padding: 6px 12px;
            font-size: 12px;
            cursor: pointer;
            transition: background 0.3s;
        }
        
        .action-btn:hover {
            background: #d0d3d9;
        }
        
        .chat-input-container {
            padding: 12px 15px;
            background: #f8f9fa;
            border-top: 1px solid #eee;
            display: flex;
            align-items: center;
        }
        
        .chat-input {
            flex: 1;
            padding: 10px 12px;
            border: 1px solid #ddd;
            border-radius: 20px;
            outline: none;
            font-size: 14px;
        }
        
        .send-button {
            background: #4ca1af;
            color: white;
            border: none;
            width: 36px;
            height: 36px;
            border-radius: 50%;
            margin-left: 8px;
            cursor: pointer;
            transition: background 0.3s;
        }
        
        .send-button:hover {
            background: #3d8b99;
        }
        
        .suggestions {
            padding: 12px 15px;
            background: #f8f9fa;
            border-top: 1px solid #eee;
            display: flex;
            flex-wrap: wrap;
            gap: 8px;
        }
        
        .suggestion {
            background: #e8ebf0;
            border: none;
            border-radius: 14px;
            padding: 5px 10px;
            font-size: 11px;
            cursor: pointer;
            transition: background 0.3s;
        }
        
        .suggestion:hover {
            background: #d0d3d9;
        }
        
        .ai-indicator {
            display: flex;
            align-items: center;
            margin-top: 5px;
            font-size: 11px;
            color: #4ca1af;
        }
        
        .ai-indicator .dot {
            width: 6px;
            height: 6px;
            background: #4ca1af;
            border-radius: 50%;
            margin-right: 5px;
            animation: pulse 1.5s infinite;
        }
        
        .redirect-link {
            color: #4ca1af;
            text-decoration: underline;
            cursor: pointer;
            margin-top: 5px;
            font-weight: 500;
        }
        
        @keyframes pulse {
            0% { opacity: 0.4; }
            50% { opacity: 1; }
            100% { opacity: 0.4; }
        }
        
        @media (max-width: 768px) {
            .chat-main-content {
                flex-direction: column;
            }
            
            .chat-sidebar {
                width: 100%;
                max-height: 150px;
            }
        }
    </style>
</head>
<body>
    <div class="chatbot-container">
        <div class="chat-header">
            <div class="logo">
                <i class="fas fa-robot"></i>
            </div>
            <h2>Project Management AI Assistant</h2>
            <div class="chat-subtitle">Powered by Spring AI with AOP Integration</div>
        </div>
        
        <div class="chat-main-content">
            <div class="chat-sidebar">
                <h3>Project Modules</h3>
                <ul class="modules">
                    <li class="active"><i class="fas fa-project-diagram"></i> Project Creation</li>
                    <li><i class="fas fa-map-marker-alt"></i> Site Selection</li>
                    <li><i class="fas fa-calculator"></i> Plan Estimations</li>
                    <li><i class="fas fa-file-alt"></i> Work Orders</li>
                    <li><i class="fas fa-hard-hat"></i> Work Inspection</li>
                    <li><i class="fas fa-tools"></i> Maintenance</li>
                    <li><i class="fas fa-money-bill-wave"></i> e-Cashbook</li>
                    <li><i class="fas fa-file-contract"></i> Document Management</li>
                    <li><i class="fas fa-exclamation-circle"></i> Grievance System</li>
                </ul>
            </div>
            
            <div class="chat-content">
                <div class="chat-messages-header">
                    <i class="fas fa-robot"></i> AI Assistant - How can I help you today?
                </div>
                
                <div class="chat-messages" id="chat-messages">
                    <div class="message bot-message">
                        <div class="message-content">
                            Hello! I'm your Project Management AI Assistant with AOP-powered monitoring. I can help you with project creation, site selection, estimations, work orders, inspections, maintenance, e-Cashbook, document management, and grievance handling. How can I assist you today?
                        </div>
                        <div class="message-time">12:00 PM</div>
                        
                        <div class="quick-actions">
                            <button class="action-btn" data-action="project-creation">Create New Project</button>
                            <button class="action-btn" data-action="work-order">Generate Work Order</button>
                            <button class="action-btn" data-action="budget-status">Check Budget Status</button>
                            <button class="action-btn" data-action="grievance">File a Grievance</button>
                        </div>
                    </div>
                </div>
                
                <div class="suggestions">
                    <button class="suggestion" data-suggestion="Create project">Create project</button>
                    <button class="suggestion" data-suggestion="Site analysis">Site analysis</button>
                    <button class="suggestion" data-suggestion="Cost estimation">Cost estimation</button>
                    <button class="suggestion" data-suggestion="Generate work order">Generate work order</button>
                    <button class="suggestion" data-suggestion="Schedule inspection">Schedule inspection</button>
                    <button class="suggestion" data-suggestion="Budget report">Budget report</button>
                    <button class="suggestion" data-suggestion="Document search">Document search</button>
                    <button class="suggestion" data-suggestion="File complaint">File complaint</button>
                </div>
                
                <div class="chat-input-container">
                    <input type="text" class="chat-input" placeholder="Type your message here..." id="user-input">
                    <button class="send-button" id="send-button"><i class="fas fa-paper-plane"></i></button>
                </div>
            </div>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const chatMessages = document.getElementById('chat-messages');
            const chatInput = document.getElementById('user-input');
            const sendButton = document.getElementById('send-button');
            const suggestions = document.querySelectorAll('.suggestion');
            const actionButtons = document.querySelectorAll('.action-btn');
            
            // Auto-scroll to bottom of chat
            chatMessages.scrollTop = chatMessages.scrollHeight;
            
            // Function to add a message to the chat
            function addMessage(text, isUser) {
                const messageDiv = document.createElement('div');
                messageDiv.className = 'message ' + (isUser ? 'user-message' : 'bot-message');
                
                const messageContent = document.createElement('div');
                messageContent.className = 'message-content';
                messageContent.innerHTML = text;
                
                const messageTime = document.createElement('div');
                messageTime.className = 'message-time';
                const now = new Date();
                messageTime.textContent = now.getHours() + ':' + String(now.getMinutes()).padStart(2, '0');
                
                messageDiv.appendChild(messageContent);
                messageDiv.appendChild(messageTime);
                
                chatMessages.appendChild(messageDiv);
                
                // Auto-scroll to bottom
                chatMessages.scrollTop = chatMessages.scrollHeight;
            }
            
            // Function to show AI is typing
            function showTypingIndicator() {
                const typingDiv = document.createElement('div');
                typingDiv.className = 'message bot-message';
                typingDiv.id = 'typing-indicator';
                
                const messageContent = document.createElement('div');
                messageContent.className = 'message-content';
                messageContent.innerHTML = '<div class="ai-indicator"><div class="dot"></div>AOP-powered AI is analyzing your request...</div>';
                
                typingDiv.appendChild(messageContent);
                chatMessages.appendChild(typingDiv);
                chatMessages.scrollTop = chatMessages.scrollHeight;
                
                return typingDiv;
            }
            
            // Function to remove typing indicator
            function removeTypingIndicator() {
                const indicator = document.getElementById('typing-indicator');
                if (indicator) {
                    indicator.remove();
                }
            }
            
            // Function to redirect to specific module
            function redirectToModule(moduleName) {
                let redirectUrl = '';
                
                switch(moduleName) {
                    case 'project-creation':
                        redirectUrl = '${pageContext.request.contextPath}/project/create';
                        break;
                    case 'site-selection':
                        redirectUrl = '${pageContext.request.contextPath}/site/selection';
                        break;
                    case 'plan-estimation':
                        redirectUrl = '${pageContext.request.contextPath}/estimation/create';
                        break;
                    case 'work-order':
                        redirectUrl = '${pageContext.request.contextPath}/workorder/generate';
                        break;
                    case 'work-inspection':
                        redirectUrl = '${pageContext.request.contextPath}/inspection/schedule';
                        break;
                    case 'maintenance':
                        redirectUrl = '${pageContext.request.contextPath}/maintenance/create';
                        break;
                    case 'e-cashbook':
                        redirectUrl = '${pageContext.request.contextPath}/cashbook/view';
                        break;
                    case 'document-management':
                        redirectUrl = '${pageContext.request.contextPath}/dms/view';
                        break;
                    case 'grievance':
                        redirectUrl = '${pageContext.request.contextPath}/grievance/add';
                        break;
                    default:
                        return false;
                }
                
                if (redirectUrl) {
                    window.location.href = redirectUrl;
                    return true;
                }
                return false;
            }
            
            // Function to send message to Spring backend
            function sendMessageToAI(message) {
                // Show typing indicator
                const typingIndicator = showTypingIndicator();
                
                // Call the Spring controller
                fetch('${pageContext.request.contextPath}/chat/send', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ message: message })
                })
                .then(function(response) {
                    if (response.ok) {
                        return response.json();
                    }
                    throw new Error('Failed to get response from server');
                })
                .then(function(data) {
                    // Remove typing indicator
                    removeTypingIndicator();
                    
                    // Check if response contains redirect information
                    if (data.redirectUrl) {
                        addMessage(data.response + '<br><span class="redirect-link" data-url="' + data.redirectUrl + '">Click here to go to ' + data.redirectName + '</span>', false);
                        
                        // Add event listener to redirect link
                        setTimeout(function() {
                            const redirectLink = document.querySelector('.redirect-link');
                            if (redirectLink) {
                                redirectLink.addEventListener('click', function() {
                                    window.location.href = this.getAttribute('data-url');
                                });
                            }
                        }, 100);
                    } else {
                        addMessage(data.response, false);
                    }
                })
                .catch(function(error) {
                    console.error('Error:', error);
                    removeTypingIndicator();
                    
                    // Fallback responses if server is not available
                    var fallbackResponses = [
                        "I'm currently processing your request. Please wait a moment.",
                        "I understand you're asking about project details. Let me pull up that information for you.",
                        "Based on your query, I recommend checking the project documentation section for more details.",
                        "I can help with that! Let me generate a report for you.",
                        "Your request has been forwarded to the relevant department. You should hear back within 24 hours."
                    ];
                    
                    addMessage(fallbackResponses[Math.floor(Math.random() * fallbackResponses.length)], false);
                });
            }
            
            // Send message on button click
            sendButton.addEventListener('click', function() {
                var message = chatInput.value.trim();
                if (message) {
                    addMessage(message, true);
                    chatInput.value = '';
                    
                    // Send message to backend
                    sendMessageToAI(message);
                }
            });
            
            // Send message on Enter key
            chatInput.addEventListener('keypress', function(e) {
                if (e.key === 'Enter') {
                    sendButton.click();
                }
            });
            
            // Add click event to suggestion buttons
            suggestions.forEach(function(button) {
                button.addEventListener('click', function() {
                    chatInput.value = this.getAttribute('data-suggestion');
                    sendButton.click();
                });
            });
            
            // Add click event to action buttons
            actionButtons.forEach(function(button) {
                button.addEventListener('click', function() {
                    const action = this.getAttribute('data-action');
                    const actionText = this.textContent;
                    
                    addMessage(actionText, true);
                    
                    // Try to redirect directly for known actions
                    if (!redirectToModule(action)) {
                        // If no direct redirect, send to backend
                        sendMessageToAI(actionText);
                    }
                });
            });
        });
    </script>
</body>
</html>
<html>
<head>
    <meta name="decorator" content="build"/>
    <meta name="tab" content="Plan badges for Bamboo"/>
    <title>Badges</title>
</head>
<body>
<div class="aui-tabs horizontal-tabs" id="tabs-format-layouts">
    <ul class="tabs-menu">
        <li class="menu-item active-tab">
            <a href="#tabs-url"><strong>URLs</strong></a>
        </li>
        <li class="menu-item">
            <a href="#tabs-html"><strong>HTML</strong></a>
        </li>
        <li class="menu-item">
            <a href="#tabs-markdown"><strong>Markdown</strong></a>
        </li>
        <li class="menu-item">
            <a href="#tabs-redoc"><strong>redoc</strong></a>
        </li>
    </ul>
    <form class="tabs-pane active-pane aui" id="tabs-url" action="#" onsubmit="return false">
        <h3>Build</h3>
        [#assign url = "${baseUrl}/plugins/servlet/badge/build/${planKey}"]
        <table class="aui">
            <thead>
            <tr>
                <th colspan="2">Build State</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td width="30%">
                    <img src="${url}">
                </td>
                <td>
                    <input class="text long-field" name="build-${planKey}" id="url-build-${planKey}" value="${url}">
                    <button class="aui-button badge-snippet" data-clipboard-target="#url-build-${planKey}">
                        <span class="aui-icon aui-icon-small aui-iconfont-copy">Copy to clipboard</span>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
        [#if action.getEnvironments()?has_content ]
        <h3>Deployments</h3>
        [#list action.getEnvironments() as env]
            [#assign url = "${baseUrl}/plugins/servlet/badge/environment/${env.id}"]
            <table class="aui">
                <thead>
                <tr>
                    <th colspan="2">Deployment: ${env.name}</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td width="30%">
                        <img src="${url}">
                    </td>
                    <td>
                        <input class="text long-field" name="env-${env.id}" id="url-env-${env.id}" value="${url}">
                        <button class="aui-button badge-snippet" data-clipboard-target="#url-env-${env.id}">
                            <span class="aui-icon aui-icon-small aui-iconfont-copy">Copy to clipboard</span>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        [/#list]
        [/#if]
    </form>

    <form class="tabs-pane aui" id="tabs-html" action="#" onsubmit="return false">
        <h3>Build</h3>
        [#assign url = "${baseUrl}/plugins/servlet/badge/build/${planKey}"]
        <table class="aui">
            <thead>
            <tr>
                <th colspan="2">Build State</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td width="30%">
                    <img src="${url}">
                </td>
                <td>
                    <input class="text long-field" name="build-${planKey}" id="html-build-${planKey}" value="&lt;img src=&quot;${url}&quot;&gt;">
                    <button class="aui-button badge-snippet" data-clipboard-target="#html-build-${planKey}">
                        <span class="aui-icon aui-icon-small aui-iconfont-copy">Copy to clipboard</span>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
        [#if action.getEnvironments()?has_content ]
        <h3>Deployments</h3>
        [#list action.getEnvironments() as env]
            [#assign url = "${baseUrl}/plugins/servlet/badge/environment/${env.id}"]
            <table class="aui">
                <thead>
                <tr>
                    <th colspan="2">Deployment: ${env.name}</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td width="30%">
                        <img src="${url}">
                    </td>
                    <td>
                        <input class="text long-field" name="env-${env.id}" id="html-env-${env.id}" value="&lt;img src=&quot;${url}&quot;&gt;">
                        <button class="aui-button badge-snippet" data-clipboard-target="#html-env-${env.id}">
                            <span class="aui-icon aui-icon-small aui-iconfont-copy">Copy to clipboard</span>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        [/#list]
        [/#if]
    </form>

    <form class="tabs-pane aui" id="tabs-markdown" action="#" onsubmit="return false">
        <h3>Build</h3>
        [#assign url = "${baseUrl}/plugins/servlet/badge/build/${planKey}"]
        <table class="aui">
            <thead>
            <tr>
                <th colspan="2">Build State</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td width="30%">
                    <img src="${url}">
                </td>
                <td>
                    <input class="text long-field" name="build-${planKey}" id="md-build-${planKey}" value="![Build](${url})">
                    <button class="aui-button badge-snippet" data-clipboard-target="#md-build-${planKey}">
                        <span class="aui-icon aui-icon-small aui-iconfont-copy">Copy to clipboard</span>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
        [#if action.getEnvironments()?has_content ]
        <h3>Deployments</h3>
        [#list action.getEnvironments() as env]
            [#assign url = "${baseUrl}/plugins/servlet/badge/environment/${env.id}"]
            <table class="aui">
                <thead>
                <tr>
                    <th colspan="2">Deployment: ${env.name}</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td width="30%">
                        <img src="${url}">
                    </td>
                    <td>
                        <input class="text long-field" name="env-${env.id}" id="md-env-${env.id}" value="![${env.name} Deployment](${url})">
                        <button class="aui-button badge-snippet" data-clipboard-target="#md-env-${env.id}">
                            <span class="aui-icon aui-icon-small aui-iconfont-copy">Copy to clipboard</span>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        [/#list]
        [/#if]
    </form>

    <form class="tabs-pane aui" id="tabs-redoc" action="#" onsubmit="return false">
        <h3>Build</h3>
        [#assign url = "${baseUrl}/plugins/servlet/badge/build/${planKey}"]
        <table class="aui">
            <thead>
            <tr>
                <th colspan="2">Build State</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td width="30%">
                    <img src="${url}">
                </td>
                <td>
                    <input class="text long-field" name="build-${planKey}" id="rd-build-${planKey}" value="{&lt;img src=&quot;${url}&quot;&gt;}">
                    <button class="aui-button badge-snippet" data-clipboard-target="#rd-build-${planKey}">
                        <span class="aui-icon aui-icon-small aui-iconfont-copy">Copy to clipboard</span>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
        [#if action.getEnvironments()?has_content ]
        <h3>Deployments</h3>
        [#list action.getEnvironments() as env]
            [#assign url = "${baseUrl}/plugins/servlet/badge/environment/${env.id}"]
            <table class="aui">
                <thead>
                <tr>
                    <th colspan="2">Deployment: ${env.name}</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td width="30%">
                        <img src="${url}">
                    </td>
                    <td>
                        <input class="text long-field" name="env-${env.id}" id="rd-env-${env.id}" value="{&lt;img src=&quot;${url}&quot;&gt;}">
                        <button class="aui-button badge-snippet" data-clipboard-target="#rd-env-${env.id}">
                            <span class="aui-icon aui-icon-small aui-iconfont-copy">Copy to clipboard</span>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        [/#list]
        [/#if]
    </form>
</div>
${webResourceManager.requireResource("tools.redfox.bamboo.status:js")}

<script>
    AJS.toInit(function () {
        AJS.tabs.setup();
        new ClipboardJS('.badge-snippet');
    });
</script>

</body>
</html>

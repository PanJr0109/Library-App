export const oktaConfig = {
    clientId: '0oagxc3mu2lPHGDqu5d7',
    issuer: 'https://dev-21541539.okta.com/oauth2/default',
    redirectUri: 'https://localhost:3000/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    disableHttpsCheck: true,
}
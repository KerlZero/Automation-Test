import { test, expect } from '@playwright/test';

test('has title', async ({ page }) => {
  await page.goto('https://playwright.dev/');

  // Expect a title "to contain" a substring.
  await expect(page).toHaveTitle(/Playwright/);
});

test('get started link', async ({ page }) => {
  await page.goto('https://playwright.dev/');

  // Click the get started link.
  await page.getByRole('link', { name: 'Get started' }).click();

  // Expects page to have a heading with the name of Installation.
  await expect(page.getByRole('heading', { name: 'Installation' })).toBeVisible();
});

test('login success web example', async ({ page }) => {
  const username = process.env.NORMAL_LOGIN_USERNAME || '${normal_login_username}';
  const password = process.env.NORMAL_LOGIN_PASSWORD || '${normal_login_password}';

  await page.goto('https://example.com/login');

  await page.locator('[data-cy="username"]').click();
  await page.locator('[data-cy="username"]').fill(username);
  await page.locator('[data-cy="password"]').click();
  await page.locator('[data-cy="password"]').fill(password);
  await page.locator('[data-cy="login-button"]').click();

  await expect(page).toHaveURL(/\/dashboard$/);
  await expect(page.getByText('Welcome back')).toBeVisible();
});

test.describe('navigation', () => {
  test.beforeEach(async ({ page }) => {
    // Go to the starting url before each test.
    await page.goto('https://playwright.dev/');
  });

  test('main navigation', async ({ page }) => {
    // Assertions use the expect API.
    await expect(page).toHaveURL('https://playwright.dev/');
  });
});


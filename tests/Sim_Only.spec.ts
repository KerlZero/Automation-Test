import { test, expect, Page, Locator } from '@playwright/test';
import { generateThaiCitizenId } from "./gen_id_card";
//import { scrollUntilEnabled } from './playwright-utils';
import { getPhoneNumberText } from './playwright-utils';
import { CheckIDCardIncorrect } from './CheckEndFlow';
import { CheckErrorCart } from './CheckEndFlow';
import { getDataLayerViewItem } from './playwright-utils';

test('Test Set Up', async ({ page }) => {
await page.setViewportSize({ width: 1920, height: 1080 });

const myId: string = generateThaiCitizenId();
console.log("Generated Thai Citizen ID:", myId);

//await page.goto('https://store-sit3.true.th/sims/postpaid/mass?packageFamily=0184693');
await page.goto('https://store-sit3.true.th/sims/postpaid/mass?packageFamily=0184693&family=0184693'); //Sim 0 baht add adv payment

// 🧪 Usage
const viewItem = await getDataLayerViewItem(page);
  if (!viewItem) {
  console.log('ไม่เจอ view_item ใน dataLayer');
} else {
  console.log('เจอ view_item:', viewItem);
}

  expect(viewItem).not.toBeNull();

/*await page.getByRole('button', { name: 'ยอมรับคุกกี้ทั้งหมด' }).click();
await page.getByRole('button', { name: 'เลือก' }).first().click();

const button = page.getByRole('button', { name: 'ระบุตำแหน่ง' });
await button.waitFor({ state: 'visible' });

const phone_num_txt = await getPhoneNumberText(page);
console.log(phone_num_txt);*/
  
});

test('Sim Only 0 Baht', async ({ page }) => {
await page.setViewportSize({ width: 1920, height: 1080 });

const myId: string = generateThaiCitizenId();
console.log("Generated Thai Citizen ID:", myId);

function delay(ms: number) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

await page.goto('https://store-sit3.true.th/sims/postpaid/mass?packageFamily=0184693&family=0187874'); //Sim 0 baht

//######################## PDP Page #######################

await page.getByRole('button', { name: 'ยอมรับคุกกี้ทั้งหมด' }).click();
await page.getByRole('button', { name: 'เลือก' }).first().click(); //Click Main Pack
//await page.getByText('5G Super Netflix 1299, Unlimited data, Voice 400min').click(); //Click Pack Flash Sale

//######################## Select SIM #######################

//ระบุตำแหน่ง
/*await page.getByRole('button', { name: 'ระบุตำแหน่ง' }).click();
await page.locator('input:nth-child(11)').click();
await page.locator('input:nth-child(11)').fill('3');
await page.locator('input:nth-child(12)').fill('4');
await page.getByRole('button', { name: 'ค้นหาเบอร์' }).first().click();
await page.locator('div:has(button:has-text("เลือก"))').first().click();*/ //First list number

const button = page.getByRole('button', { name: 'ระบุตำแหน่ง' });
await button.waitFor({ state: 'visible' });

const phone_num_txt = await getPhoneNumberText(page);
console.log(phone_num_txt);

await page.getByRole('button', { name: 'เลือก' }).first().click();
await delay(3000);
//await page.getByText("096-135-3434").locator('..').getByRole('button', { name: 'เลือก' }).click(); //Fixed number

//######################## Verify Page #######################

await page.getByRole('textbox', { name: 'เลขบัตรประชาชน' }).click();
await page.getByRole('textbox', { name: 'เลขบัตรประชาชน' }).fill(myId);
await page.getByRole('textbox', { name: 'วัน/เดือน/ปีเกิด' }).click();
await page.getByRole('textbox', { name: 'วัน/เดือน/ปีเกิด' }).fill('10/09/2537');
await page.getByRole('textbox', { name: 'เลขบัตรประชาชน' }).click();

const exit_1 = await CheckIDCardIncorrect(page, 'div.some-popup');
  if (exit_1) return; // ✅ จบ flow clean ๆ

await delay(3000);
await page.locator('div').filter({ hasText: /^ยืนยัน$/ }).nth(1).click();
await page.goto('https://store-sit3.true.th/cart');

//######################## Cart Page #######################

const exit_2 = await CheckErrorCart(page, 'div.some-popup');
  if (exit_2) return; // ✅ จบ flow clean ๆ

await expect(page.locator('[id="__nuxt"]')).toContainText('ดำเนินการชำระเงิน');

const text_cart = page.getByText('ตะกร้าสินค้า ( 1 รายการ )');
await text_cart.waitFor({ state: 'visible' });
//await expect(page.locator('#cart_lb_sim_number_undefined')).toContainText('เบอร์ 099-820-9067');

const btn_submit_cart = page.locator('div').filter({ hasText: /^ดำเนินการต่อ$/ }).nth(3);
await btn_submit_cart.waitFor({ state: 'visible' });
await page.locator('div').filter({ hasText: /^ดำเนินการต่อ$/ }).nth(3).click();

//######################## Checkout Page #######################

const text_checkout = page.getByRole('heading', { name: 'รายละเอียดช่องทางการรับสินค้า' });
await text_checkout.waitFor({ state: 'visible', });

await page.getByRole('textbox', { name: 'ชื่อ *' }).click();
await page.getByRole('textbox', { name: 'ชื่อ *' }).fill('Simple');
await page.getByRole('textbox', { name: 'นามสกุล *' }).click();
await page.getByRole('textbox', { name: 'นามสกุล *' }).fill('Run');
await page.getByRole('textbox', { name: 'เบอร์โทรศัพท์ *' }).click();
await page.getByRole('textbox', { name: 'เบอร์โทรศัพท์ *' }).fill('0932094456');
await page.getByRole('textbox', { name: 'อีเมล *' }).click();
await page.getByRole('textbox', { name: 'อีเมล *' }).fill('saran.chi@ascendcorp.com');
await delay(3000);

//Submit Checkout
await page.getByRole('button', { name: 'ยืนยัน' }).click();
await delay(10000);

//######################## Tnc Page #######################

const text_tncpage = page.getByRole('heading', { name: 'กรุณาอ่านข้อกำหนดและเงื่อนไข' });
await text_tncpage.waitFor({ state: 'visible' , timeout: 15_000 });

await page.getByRole('button', { name: 'ยอมรับและดำเนินการต่อ' }).click();

//######################## App form Page #######################

const btn = page.getByRole('button', { name: 'ยอมรับและดำเนินการต่อ' }); // throw func
//await scrollUntilEnabled(page, btn);

await btn.click(); // ✅ now safe to click

//######################## Thank You Page #######################

const text_thankyoupage = page.getByText('ทำรายการสำเร็จ');
await text_thankyoupage.waitFor({ state: 'visible' });

//await expect(page.locator('[id="__nuxt"]')).toContainText('T25112612012');

async function getLocatorText(locator: Locator, timeout = 10_000): Promise<string> {
  await locator.waitFor({ state: 'visible', timeout });
  return (await locator.textContent())?.trim() ?? '';
}

const order_no = page.locator('[id="__nuxt"]');
const phone_0 = await getLocatorText(order_no);
console.log(order_no);

//######################## Check End Flow #######################

// ใช้งาน
await delay(3000);
await page.screenshot({ path: 'page_select_sim.png', fullPage: true });
await test.info().attach('หน้าหลัก', { path: 'page_select_sim.png' });
console.log("End Flow")
  
});
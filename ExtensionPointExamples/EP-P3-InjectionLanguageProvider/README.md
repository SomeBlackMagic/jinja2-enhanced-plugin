# Injection Example

## Goal
Validate Jinja2 injection in host files via:
- manual enable from notification banner
- EP-driven automatic enable

## Files
- `example.html`
- `example.yml`
- `SampleInjectionProvider.java`

## How To Verify
1. Open `example.html` in IDE.
2. Confirm a notification appears: `Jinja2 template syntax detected...`.
3. Click `Enable Jinja2 Highlighting`.
4. Verify Jinja2 fragments are highlighted inside HTML.

## EP Verification
1. Register `SampleInjectionProvider` in another plugin using:
   - EP: `com.jinja2enhanced.injectionLanguageProvider`
2. Open a YAML file matching provider glob (`**/tasks/*.yml`).
3. Verify Jinja2 highlighting is enabled automatically.
4. Confirm no notification banner is shown for that file (EP already covers it).

# Module Structure and Cyclic Dependency Solution for Android Project

This document outlines the suggested module structure and a solution to address potential cyclic dependencies in your Android project.

## Module Dependencies

Here's a breakdown of the module dependencies:

1. **App Module (`app`)**
    - Depends on: `:theme`, `:features:auth`, `:features:profile`, `:features:home`, `:features:attendanceSummary`, `:features:salarySummary`
    - This module integrates all other modules and builds the final application.

2. **Theme Module (`theme`)**
    - Depends on: (None)
    - Contains UI theme resources and styles.

3. **Features Modules (`auth`, `profile`, `home`, `attendanceSummary`, `salarySummary`)**
    - Depend on: `:common`, `:storage`, `:network` (if they need to make API calls directly)
    - Contain UI and logic specific to each feature.

4. **Common Module (`common`)**
    - Depends on: (None)
    - Contains shared code, utilities, and data models used across multiple features.

5. **Storage Module (`storage`)**
    - Depends on: `:common` (for data models)
    - Contains data storage logic, including Preferences DataStore for auth token management.

6. **Network Module (`network`)**
    - Depends on: `:common` (for data models), `:storage` (for auth token access)
    - Contains network-related code, including the Ktor client and API service.

## Addressing Cyclic Dependency

A potential cyclic dependency can arise because the `network` module needs the auth token from the `storage` module, and the `storage` module might need to access the `network` module to refresh the token or perform other network operations.

### Solution

1. **Interface in `storage`:** Define an interface in the `storage` module for auth token management.
2. **Implementation in `storage`:** Implement this interface in the `storage` module using Preferences DataStore.
3. **Inject `AuthTokenProvider` in `network`:** In the `network` module, inject the `AuthTokenProvider` interface into your `KtorClient` or `ApiServiceImpl`.
4. **Hilt Module in `storage`:** Create a Hilt module in the `storage` module to provide the `AuthTokenProvider` implementation.

## Benefits of this Approach

- **Breaks Cyclic Dependency:** The `network` module now depends on an interface from the `storage` module, avoiding a direct circular dependency.
- **Abstraction:** The `AuthTokenProvider` interface provides an abstraction for auth token management, making it easier to change the underlying implementation if needed.
- **Testability:** You can easily mock the `AuthTokenProvider` interface for testing purposes.
- 
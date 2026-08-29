// Pathing
// _______
// src/features/home-page/search-bar/SearchInput.tsx

import styles from './SearchBar.module.css'

const SearchInput = () => {
    return (
        <input
            className={styles.searchInput}
            type="text"
            placeholder="Ask anything about Jonas .."
            aria-label="Search"
        />
    )
}

export default SearchInput
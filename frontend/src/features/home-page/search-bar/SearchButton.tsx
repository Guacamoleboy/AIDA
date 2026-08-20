// Pathing
// _______
// src/features/home-page/search-bar/SearchButton.tsx

import styles from './SearchBar.module.css'

const SearchButton = () => {
    return (
        <button
            className={styles.searchButton}
            type="submit"
            aria-label="Search"
        >
            
            <i className="fa fa-search" aria-hidden="true" />

        </button>

    )
}

export default SearchButton